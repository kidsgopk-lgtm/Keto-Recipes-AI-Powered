package com.keto.ai.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_plans")
data class MealPlan(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val date: String,
    val breakfastRecipeId: Long?,
    val lunchRecipeId: Long?,
    val dinnerRecipeId: Long?,
    val snackRecipeId: Long?
)
