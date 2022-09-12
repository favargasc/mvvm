package com.example.mvvm.models

data class Invoice(
    val id: String = "",
    val date: String = "",
    val user: User? = null,
    val cartMeals: List<CartMeal> = emptyList(),
    val total: Double = 0.0,
    val main: Double = 0.0
)