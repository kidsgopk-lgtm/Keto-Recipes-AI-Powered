package com.keto.ai.recipes.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import com.keto.ai.recipes.presentation.AIAssistant
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommunityForumScreen(posts: List<String>, onPost: (String) -> Unit) {
    var newPost by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Community Forum", style = MaterialTheme.typography.h5)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = newPost,
            onValueChange = { newPost = it },
            label = { Text("Share your tip or recipe...") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            if (newPost.isNotBlank()) {
                onPost(newPost)
                newPost = ""
            }
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Post")
        }
        Spacer(Modifier.height(24.dp))
        Text("Recent Posts:", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
    AIAssistant(prompt = "Ask the AI for keto tips, recipe help, or community moderation.")
        posts.forEach { post ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = 2.dp) {
                Text(post, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
