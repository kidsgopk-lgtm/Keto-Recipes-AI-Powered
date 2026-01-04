package com.keto.ai.recipes.presentation

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(modifier = Modifier)
}

@Composable
fun GoalProgressIndicator(progress: Float) {
    LinearProgressIndicator(progress = progress, modifier = Modifier)
}
