package com.keto.ai.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val weight: Double,
    val height: Double,
    val goal: String,
    val dailyCalories: Int,
    val dailyFat: Double,
    val dailyCarbs: Double,
    val dailyProtein: Double
)
