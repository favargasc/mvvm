package com.example.mvvm.models

data class Invoice(
    val id: String = "",
    val date: String = "",
    val user: User? = null,
    val cartMeals: List<CartMeal> = emptyList()
)