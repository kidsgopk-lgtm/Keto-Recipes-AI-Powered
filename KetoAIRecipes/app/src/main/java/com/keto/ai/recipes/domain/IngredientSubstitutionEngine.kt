package com.keto.ai.recipes.domain

import com.keto.ai.recipes.model.Recipe

class IngredientSubstitutionEngine {
    fun suggestSubstitutes(missing: String): List<String> {
        // Return keto-friendly alternatives for missing ingredient
        return when (missing.lowercase()) {
            "sugar" -> listOf("erythritol", "stevia")
            "flour" -> listOf("almond flour", "coconut flour")
            "milk" -> listOf("almond milk", "coconut milk")
            else -> listOf("No substitute found")
        }
    }
}
