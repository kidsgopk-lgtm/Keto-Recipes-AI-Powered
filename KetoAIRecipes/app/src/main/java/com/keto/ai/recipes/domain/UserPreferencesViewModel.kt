package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.UserPreferences

class UserPreferencesViewModel : ViewModel() {
    val preferences = MutableLiveData<UserPreferences>(UserPreferences())
    fun updateAllergies(allergies: List<String>) {
        preferences.value = preferences.value?.copy(allergies = allergies)
    }
    fun updateRestrictions(restrictions: List<String>) {
        preferences.value = preferences.value?.copy(restrictions = restrictions)
    }
    fun updateBudget(budget: Double) {
        preferences.value = preferences.value?.copy(budget = budget)
    }
    fun updateTasteProfile(tasteProfile: Map<String, Int>) {
        preferences.value = preferences.value?.copy(tasteProfile = tasteProfile)
    }
}
