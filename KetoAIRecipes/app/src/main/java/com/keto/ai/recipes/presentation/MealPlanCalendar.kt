package com.keto.ai.recipes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.keto.ai.recipes.model.MealPlan

@Composable
fun MealPlanCalendar(mealPlan: MealPlan, onMealClick: (String, String) -> Unit) {
    Column {
        Text("Weekly Meal Plan", style = MaterialTheme.typography.h5)
        // For each day, show breakfast, lunch, dinner, snack
        // Use LazyColumn or LazyRow for days
        // onMealClick(day, mealType) for interaction
    }
}
