package com.example.mvvm.models

data class Meal(
    val ID: String="",
    val name: String="",
    val availability: Boolean=false,
    val type: String ="",
    val cost: Double = 0.0
//agregar el tiempo
)