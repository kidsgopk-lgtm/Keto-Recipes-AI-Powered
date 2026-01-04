package com.keto.ai.recipes.model

data class Message(
    val id: String,
    val senderId: String,
    val receiverId: String?, // null for group
    val groupId: String?,
    val content: String,
    val timestamp: Long
)
