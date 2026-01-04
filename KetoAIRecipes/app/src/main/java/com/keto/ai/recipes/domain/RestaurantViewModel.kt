package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.Restaurant

class RestaurantViewModel : ViewModel() {
    val restaurants = MutableLiveData<List<Restaurant>>(emptyList())
    fun addRestaurant(restaurant: Restaurant) {
        restaurants.value = restaurants.value?.plus(restaurant)
    }
}
