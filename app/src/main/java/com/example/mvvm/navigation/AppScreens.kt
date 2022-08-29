package com.example.mvvm.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens(route = "login_screen")
    object MealsManagerScreen: AppScreens(route = "meals_manager_screen")
    object MainMenuScreen: AppScreens(route = "main_menu_screen")
    object RegisterScreen: AppScreens(route = "register_screen")
    object ShoppingCartScreen: AppScreens(route = "shopping_cart_screen")
}
