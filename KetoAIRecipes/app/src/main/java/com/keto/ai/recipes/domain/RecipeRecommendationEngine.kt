package com.keto.ai.recipes.domain

import com.keto.ai.recipes.model.Recipe
import com.keto.ai.recipes.model.UserPreferences

class RecipeRecommendationEngine {
    fun recommend(recipes: List<Recipe>, prefs: UserPreferences): List<Recipe> {
        // Filter recipes by allergies, restrictions, budget, and taste profile
        return recipes.filter { recipe ->
            val matchesAllergies = prefs.allergies.none { recipe.ingredients.contains(it) }
            val matchesRestrictions = prefs.restrictions.none { recipe.tags.contains(it) }
            val matchesBudget = prefs.budget == null || recipe.estimatedCost <= prefs.budget
            val matchesTaste = prefs.tasteProfile.all { (taste, score) -> recipe.tasteProfile[taste] ?: 0 >= score }
            matchesAllergies && matchesRestrictions && matchesBudget && matchesTaste
        }
    }
}
