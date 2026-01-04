package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keto.ai.recipes.domain.UserProfileViewModel

@Composable
fun ProfileScreen() {
    val viewModel: UserProfileViewModel = viewModel()
    val profile by viewModel.profile
    Column(Modifier.padding(16.dp)) {
        Text("Profile", style = MaterialTheme.typography.h5)
        profile?.let {
            Text("Name: ${it.name}")
            Text("Streak: ${it.streak}")
            Text("Weekly Goal: ${it.weeklyGoal}")
            Text("Achievements: ${it.achievements.joinToString()}")
            Text("Badges: ${it.badges.joinToString()}")
        }
    }
}
