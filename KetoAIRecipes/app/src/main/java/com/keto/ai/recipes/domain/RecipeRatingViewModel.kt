package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.RecipeRating

class RecipeRatingViewModel : ViewModel() {
    val ratings = MutableLiveData<List<RecipeRating>>(emptyList())
    fun addRating(rating: RecipeRating) {
        ratings.value = ratings.value?.plus(rating)
    }
}
