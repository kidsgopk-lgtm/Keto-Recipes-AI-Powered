package com.keto.ai.recipes.domain

import com.keto.ai.recipes.model.MealPlan
import com.keto.ai.recipes.data.RecipeDao

object GroceryListGenerator {
    fun generateGroceryList(mealPlan: MealPlan, recipeDao: RecipeDao): List<GroceryItem> {
        val recipeIds = listOfNotNull(
            mealPlan.breakfastRecipeId,
            mealPlan.lunchRecipeId,
            mealPlan.dinnerRecipeId,
            mealPlan.snackRecipeId
        )
        val ingredients = recipeIds.flatMap { recipeId ->
            recipeDao.getIngredientsForRecipe(recipeId)
        }
        return ingredients.groupBy { it.name to it.unit }
            .map { (key, items) ->
                GroceryItem(key.first, items.sumOf { it.quantity }, key.second)
            }
    }
}

data class GroceryItem(val name: String, val quantity: Double, val unit: String)
