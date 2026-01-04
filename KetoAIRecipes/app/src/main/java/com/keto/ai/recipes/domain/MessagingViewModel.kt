package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.Message

class MessagingViewModel : ViewModel() {
    val messages = MutableLiveData<List<Message>>(emptyList())
    fun sendMessage(message: Message) {
        messages.value = messages.value?.plus(message)
    }
}
