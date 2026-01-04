package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keto.ai.recipes.domain.MessagingViewModel
import com.keto.ai.recipes.model.Message

@Composable
fun MessagingScreen(userId: String, groupId: String? = null) {
    val viewModel: MessagingViewModel = viewModel()
    val messages by viewModel.messages
    var messageText = ""
    Column(Modifier.padding(16.dp)) {
        Text("Chat", style = MaterialTheme.typography.h5)
        // ... UI for message input ...
        Button(onClick = {
            viewModel.sendMessage(Message(
                id = System.currentTimeMillis().toString(),
                senderId = userId,
                receiverId = null,
                groupId = groupId,
                content = messageText,
                timestamp = System.currentTimeMillis()
            ))
        }) {
            Text("Send")
        }
        Text("Messages:")
        messages.filter { it.groupId == groupId || it.receiverId == userId }.forEach {
            Text("${it.senderId}: ${it.content}")
        }
    }
}
