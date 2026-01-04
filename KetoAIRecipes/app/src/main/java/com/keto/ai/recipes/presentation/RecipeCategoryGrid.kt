package com.keto.ai.recipes.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCategoryGrid(categories: List<String>, onCategoryClick: (String) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxWidth()) {
        items(categories.size) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onCategoryClick(categories[index]) },
                elevation = 2.dp
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.height(80.dp)) {
                    Text(categories[index], style = MaterialTheme.typography.h6)
                }
            }
        }
    }
}
