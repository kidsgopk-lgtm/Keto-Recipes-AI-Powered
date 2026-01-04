package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keto.ai.recipes.domain.RecipeRatingViewModel
import com.keto.ai.recipes.model.RecipeRating

@Composable
fun RecipeRatingScreen(recipeId: String, userId: String) {
    val viewModel: RecipeRatingViewModel = viewModel()
    val ratings by viewModel.ratings
    var rating = 0
    var comment = ""
    Column(Modifier.padding(16.dp)) {
        Text("Rate this Recipe", style = MaterialTheme.typography.h6)
        // ... UI for rating and comment ...
        Button(onClick = {
            viewModel.addRating(RecipeRating(recipeId, userId, rating, comment))
        }) {
            Text("Submit Rating")
        }
        Text("All Ratings:")
        ratings.filter { it.recipeId == recipeId }.forEach {
            Text("${it.userId}: ${it.rating} - ${it.comment ?: ""}")
        }
    }
}
