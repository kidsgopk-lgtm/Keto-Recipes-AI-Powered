package com.keto.ai.recipes.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.keto.ai.recipes.model.Nutrition

@Composable
fun ProductDetailScreen(
    product: Product,
    onSave: (Product) -> Unit,
    alternatives: List<Product>
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(product.name, style = MaterialTheme.typography.h5)
        NutritionLabel(product.nutrition)
        Text("Keto Rating: ${product.ketoRating}")
        Button(onClick = { onSave(product) }) { Text("Save to My Products") }
        if (alternatives.isNotEmpty()) {
            Text("Alternatives:", style = MaterialTheme.typography.h6)
            alternatives.forEach { alt ->
                Text(alt.name)
            }
        }
    }
}
