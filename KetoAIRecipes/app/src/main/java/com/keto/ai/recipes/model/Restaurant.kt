package com.keto.ai.recipes.model

data class Restaurant(
    val id: String,
    val name: String,
    val location: String,
    val ketoOptions: List<String>
)
