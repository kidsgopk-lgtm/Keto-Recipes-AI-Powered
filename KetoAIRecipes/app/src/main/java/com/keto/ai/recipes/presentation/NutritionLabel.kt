package com.keto.ai.recipes.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keto.ai.recipes.model.Nutrition

@Composable
fun NutritionLabel(nutrition: Nutrition) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp), elevation = 2.dp) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Nutrition Facts", style = MaterialTheme.typography.h6)
            Divider()
            Text("Calories: ${nutrition.calories}")
            Text("Fat: ${nutrition.fat}g")
            Text("Carbs: ${nutrition.carbs}g")
            Text("Protein: ${nutrition.protein}g")
            Text("Fiber: ${nutrition.fiber}g")
            Text("Sugar: ${nutrition.sugar}g")
        }
    }
}
