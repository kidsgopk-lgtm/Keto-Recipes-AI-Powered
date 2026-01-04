package com.keto.ai.recipes.domain

import com.keto.ai.recipes.model.Recipe
import com.keto.ai.recipes.data.RecipeDao
import com.keto.ai.recipes.data.UserDao

enum class InteractionType { LIKE, DISLIKE, VIEW }

data class UserPreferences(
    val preferredIngredients: List<String>,
    val nutritionGoals: NutritionGoals,
    val goalType: GoalType
)

data class NutritionGoals(
    val calories: Int,
    val fat: Double,
    val carbs: Double,
    val protein: Double
)

enum class GoalType { WEIGHT_LOSS, MAINTENANCE }

interface UserInteractionStore {
    fun getHistory(userId: String): List<Pair<Long, InteractionType>>
    fun record(userId: String, recipeId: String, interactionType: InteractionType)
    fun updatePreferences(userId: String, preferences: UserPreferences)
    fun getPreferences(userId: String): UserPreferences
}

class RecommendationEngine(
    private val recipeDao: RecipeDao,
    private val userDao: UserDao,
    private val interactionStore: UserInteractionStore
) {
    fun getRecommendedRecipes(userId: String): List<Recipe> {
        val user = userDao.getById(userId.toLong()) ?: return getColdStartRecipes()
        val preferences = getUserPreferences(userId)
        val history = interactionStore.getHistory(userId)

        val collaborative = getCollaborativeRecommendations(userId, history)
        val contentBased = getContentBasedRecommendations(preferences)
        val personalized = personalizeRecommendations(collaborative + contentBased, preferences.goalType)

        return if (personalized.isNotEmpty()) personalized else getColdStartRecipes()
    }

    fun recordUserInteraction(userId: String, recipeId: String, interactionType: InteractionType) {
        interactionStore.record(userId, recipeId, interactionType)
    }

    fun updatePreferences(userId: String, preferences: UserPreferences) {
        interactionStore.updatePreferences(userId, preferences)
    }

    private fun getCollaborativeRecommendations(userId: String, history: List<Pair<Long, InteractionType>>): List<Recipe> {
        return recipeDao.getAll().filter { it.isFavorite }
    }

    private fun getContentBasedRecommendations(preferences: UserPreferences): List<Recipe> {
        return recipeDao.getAll().filter { recipe ->
            preferences.preferredIngredients.any { it in recipe.title || it in recipe.description }
        }
    }

    private fun personalizeRecommendations(recipes: List<Recipe>, goalType: GoalType): List<Recipe> {
        return when (goalType) {
            GoalType.WEIGHT_LOSS -> recipes.sortedBy { it.servings }
            GoalType.MAINTENANCE -> recipes
        }
    }

    private fun getColdStartRecipes(): List<Recipe> {
        return recipeDao.getAll().take(10)
    }

    private fun getUserPreferences(userId: String): UserPreferences {
        return interactionStore.getPreferences(userId)
    }
}
