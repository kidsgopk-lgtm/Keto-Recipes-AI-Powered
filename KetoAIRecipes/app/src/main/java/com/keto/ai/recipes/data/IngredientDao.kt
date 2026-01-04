package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.Ingredient

@Dao
interface IngredientDao {
    @Insert suspend fun insert(ingredient: Ingredient): Long
    @Update suspend fun update(ingredient: Ingredient)
    @Delete suspend fun delete(ingredient: Ingredient)
    @Query("SELECT * FROM ingredients WHERE recipeId = :recipeId") suspend fun getByRecipeId(recipeId: Long): List<Ingredient>
}
