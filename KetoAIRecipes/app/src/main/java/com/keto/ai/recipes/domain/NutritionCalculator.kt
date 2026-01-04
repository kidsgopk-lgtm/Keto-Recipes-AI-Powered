package com.keto.ai.recipes.domain

import com.keto.ai.recipes.model.MealPlan
import com.keto.ai.recipes.model.Nutrition
import com.keto.ai.recipes.data.RecipeDao

object NutritionCalculator {
    fun calculateMealPlanNutrition(mealPlan: MealPlan, recipeDao: RecipeDao): Nutrition {
        val recipeIds = listOfNotNull(
            mealPlan.breakfastRecipeId,
            mealPlan.lunchRecipeId,
            mealPlan.dinnerRecipeId,
            mealPlan.snackRecipeId
        )
        val nutritions = recipeIds.mapNotNull { recipeDao.getNutritionForRecipe(it) }
        return Nutrition(
            id = 0,
            recipeId = 0,
            calories = nutritions.sumOf { it.calories },
            fat = nutritions.sumOf { it.fat },
            carbs = nutritions.sumOf { it.carbs },
            protein = nutritions.sumOf { it.protein },
            fiber = nutritions.sumOf { it.fiber },
            sugar = nutritions.sumOf { it.sugar }
        )
    }
}
