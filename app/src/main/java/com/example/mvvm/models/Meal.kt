package com.example.mvvm.models

data class Meal(
    val ID: String,
    val name: String,
    val availability: Boolean,
    val type: String,
    val cost: Double
)
