package com.keto.ai.recipes.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.keto.ai.recipes.model.Recipe

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(@Query("page") page: Int, @Query("size") size: Int): List<Recipe>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Long): Recipe

    @GET("recipes/search")
    suspend fun searchRecipes(@Query("query") query: String): List<Recipe>
}
