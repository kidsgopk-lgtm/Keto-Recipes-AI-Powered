package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.Nutrition

@Dao
interface NutritionDao {
    @Insert suspend fun insert(nutrition: Nutrition): Long
    @Update suspend fun update(nutrition: Nutrition)
    @Delete suspend fun delete(nutrition: Nutrition)
    @Query("SELECT * FROM nutrition WHERE recipeId = :recipeId") suspend fun getByRecipeId(recipeId: Long): Nutrition?
}
