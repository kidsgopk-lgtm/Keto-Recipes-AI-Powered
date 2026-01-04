package com.keto.ai.recipes.model

data class UserPreferences(
    val allergies: List<String> = emptyList(),
    val restrictions: List<String> = emptyList(),
    val budget: Double? = null,
    val tasteProfile: Map<String, Int> = emptyMap() // e.g. sweet, spicy, savory
)
