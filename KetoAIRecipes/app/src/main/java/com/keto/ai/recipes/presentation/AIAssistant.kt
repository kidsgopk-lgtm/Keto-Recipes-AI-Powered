package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun AIAssistant(
    prompt: String = "How can I help you today?",
    onQuery: (String) -> Unit = {}
) {
    val query = remember { mutableStateOf(TextFieldValue()) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(prompt, style = MaterialTheme.typography.h6)
        TextField(
            value = query.value,
            onValueChange = { query.value = it },
            label = { Text("Ask AI") }
        )
        Button(onClick = { onQuery(query.value.text) }) {
            Text("Send")
        }
    }
}
