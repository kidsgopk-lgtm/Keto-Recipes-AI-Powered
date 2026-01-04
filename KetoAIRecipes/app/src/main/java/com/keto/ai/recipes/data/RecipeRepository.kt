package com.keto.ai.recipes.data

import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(
    private val api: RecipeApiService,
    private val recipeDao: RecipeDao
) {
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<String?>()

    suspend fun fetchRecipes(page: Int, size: Int): List<Recipe> = withContext(Dispatchers.IO) {
        loadingState.postValue(true)
        try {
            val remoteRecipes = api.getRecipes(page, size)
            recipeDao.insertAll(remoteRecipes)
            loadingState.postValue(false)
            remoteRecipes
        } catch (e: Exception) {
            errorState.postValue(e.message)
            loadingState.postValue(false)
            recipeDao.getAll()
        }
    }

    suspend fun getRecipeById(id: Long): Recipe? = withContext(Dispatchers.IO) {
        try {
            api.getRecipeById(id)
        } catch (e: Exception) {
            recipeDao.getById(id)
        }
    }

    suspend fun searchRecipes(query: String): List<Recipe> = withContext(Dispatchers.IO) {
        try {
            api.searchRecipes(query)
        } catch (e: Exception) {
            recipeDao.searchByNameOrIngredientOrNutrition(query)
        }
    }

    suspend fun getRecipesPaged(page: Int, size: Int): List<Recipe> = fetchRecipes(page, size)
}
