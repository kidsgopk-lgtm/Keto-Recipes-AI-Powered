package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.FamilyProfile

class FamilyProfileViewModel : ViewModel() {
    val family = MutableLiveData<FamilyProfile>()
    fun addMember(profile: com.keto.ai.recipes.model.UserProfile) {
        val current = family.value
        if (current != null) {
            family.value = current.copy(members = current.members + profile)
        }
    }
}
