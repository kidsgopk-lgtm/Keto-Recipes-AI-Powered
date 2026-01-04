package com.keto.ai.recipes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.keto.ai.recipes.model.Recipe
import com.keto.ai.recipes.model.Ingredient
import com.keto.ai.recipes.model.Nutrition

@Composable
fun RecipeDetailScreen(recipe: Recipe, ingredients: List<Ingredient>, nutrition: Nutrition) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text(recipe.title, style = MaterialTheme.typography.h4)
        Image(painter = rememberImagePainter(recipe.imageUrl), contentDescription = recipe.title, modifier = Modifier.fillMaxWidth().height(200.dp))
        Spacer(Modifier.height(8.dp))
        Text(recipe.description)
        Spacer(Modifier.height(8.dp))
        Text("Ingredients:", style = MaterialTheme.typography.h6)
        ingredients.forEach { Text("- ${it.quantity} ${it.unit} ${it.name}") }
        Spacer(Modifier.height(8.dp))
        Text("Instructions:", style = MaterialTheme.typography.h6)
        // Add instructions field to Recipe if needed
        Spacer(Modifier.height(8.dp))
        NutritionLabel(nutrition)
    }
}
