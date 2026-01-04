package com.keto.ai.recipes.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import com.keto.ai.recipes.presentation.AIAssistant
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome to KetoAIRecipes!",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )
                Text(
                    "Discover personalized keto recipes, track your progress, and reach your goals.",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
                AIAssistant(prompt = "Welcome! Ask anything about keto, recipes, or app features.")
                Button(
                    onClick = onContinue,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Get Started")
                }
            }
        }
    }
}
