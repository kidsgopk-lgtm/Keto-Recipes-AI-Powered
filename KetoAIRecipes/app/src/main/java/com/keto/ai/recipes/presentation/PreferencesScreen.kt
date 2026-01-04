package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keto.ai.recipes.domain.UserPreferencesViewModel

@Composable
fun PreferencesScreen() {
    val viewModel: UserPreferencesViewModel = viewModel()
    val prefs by viewModel.preferences
    Column(Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Text("Dietary Preferences", style = MaterialTheme.typography.h5)
        Text("Set your allergies, restrictions, budget, and taste profile for personalized recommendations.", style = MaterialTheme.typography.body2)
        // ... UI for allergies, restrictions, budget, taste profile ...
    }
}
