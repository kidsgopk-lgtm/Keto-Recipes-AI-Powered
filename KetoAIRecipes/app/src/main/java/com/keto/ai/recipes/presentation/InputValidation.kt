package com.keto.ai.recipes.presentation

object InputValidation {
    fun validateEmail(email: String): String? {
        return if (email.isBlank()) "Email is required"
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Invalid email format"
        else null
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 8 -> "Password must be at least 8 characters"
            !password.any { it.isDigit() } -> "Password must contain a number"
            !password.any { it.isUpperCase() } -> "Password must contain an uppercase letter"
            !password.any { it.isLowerCase() } -> "Password must contain a lowercase letter"
            else -> null
        }
    }

    fun validateName(name: String): String? {
        return if (name.isBlank()) "Name is required" else null
    }

    fun validateWeight(weight: Double): String? {
        return if (weight <= 0) "Weight must be greater than 0" else null
    }

    fun validateHeight(height: Double): String? {
        return if (height <= 0) "Height must be greater than 0" else null
    }
}
