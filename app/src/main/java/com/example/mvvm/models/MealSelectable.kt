package com.example.mvvm.models

data class MealSelectable(
    val meal: Meal,
    var isSelected: Boolean = false
)