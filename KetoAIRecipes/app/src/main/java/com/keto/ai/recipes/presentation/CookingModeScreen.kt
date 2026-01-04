package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.ui.Modifier

@Composable
fun CookingModeScreen(recipeId: String) {
    // ... UI for step-by-step cooking, voice commands, timers ...
    Column(Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Text("Cooking Mode", style = MaterialTheme.typography.h5)
        // ...
    }
}
