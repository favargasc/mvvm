package com.example.mvvm.viewmodels

import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Meal

data class MealListState(
    val isLoading: Boolean = false,
    val meals: List<Meal> = emptyList(),
    val error: String = ""
)

data class CartMealListState(
    val isLoading: Boolean = false,
    val meals: List<CartMeal> = emptyList(),
    val error: String = ""
)
