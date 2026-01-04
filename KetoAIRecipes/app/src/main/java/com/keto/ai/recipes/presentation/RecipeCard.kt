package com.keto.ai.recipes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.keto.ai.recipes.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe,
    onFavoriteClick: (Recipe) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(recipe.imageUrl),
                contentDescription = recipe.title,
                modifier = Modifier.size(80.dp)
            )
            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Text(recipe.title, style = MaterialTheme.typography.h6)
                Text("Time: ${recipe.cookTime} min", style = MaterialTheme.typography.body2)
                Text("Difficulty: ${recipe.difficulty}", style = MaterialTheme.typography.body2)
            }
            IconButton(onClick = { onFavoriteClick(recipe) }) {
                Icon(
                    imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}
