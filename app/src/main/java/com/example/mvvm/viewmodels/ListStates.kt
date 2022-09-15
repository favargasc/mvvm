package com.example.mvvm.viewmodels

import com.example.mvvm.models.*

data class MealListState(
    val isLoading: Boolean = false,
    val meals: List<Meal> = emptyList(),
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