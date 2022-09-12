package com.example.mvvm.viewmodels

import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Invoice
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User

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

data class UserListState(
    val isLoading: Boolean = false,
    val meals: List<User> = emptyList(),
    val error: String = ""
)

data class InvoicesListState(
    val isLoading: Boolean = false,
    val meals: List<Invoice> = emptyList(),
    val error: String = ""
)