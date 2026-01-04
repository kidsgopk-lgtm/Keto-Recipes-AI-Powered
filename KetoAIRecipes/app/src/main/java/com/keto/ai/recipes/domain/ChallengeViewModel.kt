package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class ChallengeViewModel : ViewModel() {
    val weeklyChallenges = MutableLiveData<List<String>>(listOf("Try 3 new recipes", "Share a meal photo", "Hit your macro goals 5 days"))
    val streak = MutableLiveData<Int>(0)
    fun completeChallenge(challenge: String) {
        // ... logic to mark challenge complete and update streak ...
    }
}
