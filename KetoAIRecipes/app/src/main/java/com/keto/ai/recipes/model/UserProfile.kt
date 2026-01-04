package com.keto.ai.recipes.model

data class UserProfile(
    val id: String,
    val name: String,
    val avatarUrl: String?,
    val achievements: List<String> = emptyList(),
    val badges: List<String> = emptyList(),
    val streak: Int = 0,
    val weeklyGoal: Int = 0
)
