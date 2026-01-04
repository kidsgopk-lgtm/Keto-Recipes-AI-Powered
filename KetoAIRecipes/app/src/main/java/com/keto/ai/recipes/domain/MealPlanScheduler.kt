package com.keto.ai.recipes.domain

import com.keto.ai.recipes.model.MealPlan
import com.keto.ai.recipes.data.RecipeDao
import com.keto.ai.recipes.data.UserDao
import java.util.Date

class MealPlanScheduler(
    private val recipeDao: RecipeDao,
    private val userDao: UserDao
) {
    fun generateWeeklyMealPlan(userId: String, startDate: Date): MealPlan {
        val user = userDao.getById(userId.toLong()) ?: return MealPlan()
        val recipes = recipeDao.getAll()
        return MealPlan(
            id = 0,
            name = "Weekly Plan",
            date = startDate.toString(),
            breakfastRecipeId = recipes.randomOrNull()?.id,
            lunchRecipeId = recipes.randomOrNull()?.id,
            dinnerRecipeId = recipes.randomOrNull()?.id,
            snackRecipeId = recipes.randomOrNull()?.id
        )
    }
}
