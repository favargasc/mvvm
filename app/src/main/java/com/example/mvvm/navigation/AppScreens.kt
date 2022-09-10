package com.example.mvvm.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen : AppScreens(route = "login")
    object MealsManagerScreen : AppScreens(route = "meals_manager")
    object MainMenuScreen : AppScreens(route = "main_menu/{userId}")
    object RegisterScreen : AppScreens(route = "register")
    object ShoppingCartScreen : AppScreens(route = "shopping_cart/{userId}")
}
