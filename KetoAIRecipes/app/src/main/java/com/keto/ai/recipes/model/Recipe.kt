package com.keto.ai.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val prepTime: Int,
    val cookTime: Int,
    val servings: Int,
    val difficulty: String,
    val isFavorite: Boolean
)
