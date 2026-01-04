package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.UserProfile

class UserProfileViewModel : ViewModel() {
    val profile = MutableLiveData<UserProfile>()
    fun updateStreak(newStreak: Int) {
        profile.value = profile.value?.copy(streak = newStreak)
    }
    fun addAchievement(achievement: String) {
        val current = profile.value
        if (current != null) {
            profile.value = current.copy(achievements = current.achievements + achievement)
        }
    }
    fun addBadge(badge: String) {
        val current = profile.value
        if (current != null) {
            profile.value = current.copy(badges = current.badges + badge)
        }
    }
}
