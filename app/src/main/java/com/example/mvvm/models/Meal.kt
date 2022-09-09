package com.example.mvvm.models

enum class Type {
    MAIN, DESSERT, SNACK, DRINK
}

enum class Time {
    BREAKFAST, LUNCH, AFTERNOON_TEA , DINNER
}

data class Meal (
    val ID: String = "",
    val name: String = "",
    val availability: Boolean = true,
    val type: Int = Type.MAIN.ordinal,
    val time: Int = Time.BREAKFAST.ordinal,
    val cost: Double = 0.0,
    val img: String = ""
)

data class CartMeal (
    val id: String = "",
    val userId: String = "",
    val meal: Meal? = null,
    val count: Int = 0
)


