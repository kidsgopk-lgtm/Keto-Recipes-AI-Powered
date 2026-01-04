package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.Recipe

@Dao
interface RecipeDao {
    @Insert suspend fun insert(recipe: Recipe): Long
    @Update suspend fun update(recipe: Recipe)
    @Delete suspend fun delete(recipe: Recipe)
    @Query("SELECT * FROM recipes") suspend fun getAll(): List<Recipe>
    @Query("SELECT * FROM recipes WHERE id = :id") suspend fun getById(id: Long): Recipe?

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    suspend fun searchByNameOrIngredientOrNutrition(query: String): List<Recipe>
}
