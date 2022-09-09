package com.example.mvvm.models

data class Order (
    val ID: String = "",
    val meals: List<Meal>? = null,
    val user: User? = null,
    val date: String = ""
)
