package com.keto.ai.recipes.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityForumViewModel : ViewModel() {
    val posts = MutableLiveData<List<String>>(emptyList())

    fun addPost(post: String) {
        val updated = posts.value.orEmpty() + post
        posts.value = updated
    }
}
