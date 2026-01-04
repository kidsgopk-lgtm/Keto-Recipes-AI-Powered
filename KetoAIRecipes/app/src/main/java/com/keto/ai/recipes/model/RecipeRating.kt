package com.keto.ai.recipes.model

data class RecipeRating(
    val recipeId: String,
    val userId: String,
    val rating: Int,
    val comment: String? = null
)
