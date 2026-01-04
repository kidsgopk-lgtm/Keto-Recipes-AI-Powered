package com.keto.ai.recipes.presentation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.material.*
import com.keto.ai.recipes.presentation.AIAssistant
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.keto.ai.recipes.data.RecipeApiService
import com.keto.ai.recipes.model.Recipe

@Composable
fun RecipeImportScreen(
    onRecipeImported: (Recipe) -> Unit
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    // UI for importing via link or photo
    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Import Recipe", style = MaterialTheme.typography.h5)
            Button(onClick = { /* TODO: Implement scan photo */ }) {
                Text("Scan Recipe Photo")
            }
            Button(onClick = { /* TODO: Implement import from web link */ }) {
                Text("Import from Web Link")
            }
            AIAssistant(prompt = "Paste a recipe link or upload a photo. AI can help parse and suggest improvements.")
        }
    }
}
