package com.keto.ai.recipes.model

data class SymptomLog(
    val date: Long,
    val energyLevel: Int,
    val hungerLevel: Int,
    val notes: String? = null
)
