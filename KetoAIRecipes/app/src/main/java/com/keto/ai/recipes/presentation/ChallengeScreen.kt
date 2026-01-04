package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keto.ai.recipes.domain.ChallengeViewModel

@Composable
fun ChallengeScreen() {
    val viewModel: ChallengeViewModel = viewModel()
    val challenges by viewModel.weeklyChallenges
    val streak by viewModel.streak
    Column(Modifier.padding(16.dp)) {
        Text("Weekly Challenges", style = MaterialTheme.typography.h5)
        Text("Current Streak: $streak")
        challenges.forEach { challenge ->
            Button(onClick = { viewModel.completeChallenge(challenge) }) {
                Text(challenge)
            }
        }
    }
}
