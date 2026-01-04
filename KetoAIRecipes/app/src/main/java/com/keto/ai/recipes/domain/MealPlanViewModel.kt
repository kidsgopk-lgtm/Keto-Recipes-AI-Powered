package com.keto.ai.recipes.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keto.ai.recipes.model.MealPlan
import com.keto.ai.recipes.model.Nutrition
import com.keto.ai.recipes.data.MealPlanDao
import com.keto.ai.recipes.data.RecipeDao
import java.util.Date

class MealPlanViewModel(
    private val mealPlanDao: MealPlanDao,
    private val recipeDao: RecipeDao,
    private val scheduler: MealPlanScheduler
) : ViewModel() {

    val weeklyMealPlan = MutableLiveData<MealPlan>()

    fun generateWeeklyMealPlan(userId: String, startDate: Date) {
        val plan = scheduler.generateWeeklyMealPlan(userId, startDate)
        weeklyMealPlan.value = plan
    }

    fun generateGroceryList(mealPlan: MealPlan): List<GroceryItem> {
        return GroceryListGenerator.generateGroceryList(mealPlan, recipeDao)
    }

    fun calculateMealPlanNutrition(mealPlan: MealPlan): Nutrition {
        return NutritionCalculator.calculateMealPlanNutrition(mealPlan, recipeDao)
    }
}
