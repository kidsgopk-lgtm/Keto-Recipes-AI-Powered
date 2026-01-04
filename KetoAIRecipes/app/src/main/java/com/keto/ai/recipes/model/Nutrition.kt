package com.keto.ai.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition")
data class Nutrition(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val recipeId: Long,
    val calories: Int,
    val fat: Double,
    val carbs: Double,
    val protein: Double,
    val fiber: Double,
    val sugar: Double
)
