package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.MealPlan

@Dao
interface MealPlanDao {
    @Insert suspend fun insert(mealPlan: MealPlan): Long
    @Update suspend fun update(mealPlan: MealPlan)
    @Delete suspend fun delete(mealPlan: MealPlan)
    @Query("SELECT * FROM meal_plans WHERE date = :date") suspend fun getByDate(date: String): List<MealPlan>
}
