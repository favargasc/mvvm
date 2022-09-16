package com.example.mvvm.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen : AppScreens(route = "login")
    object MealsManagerScreen : AppScreens(route = "meals_manager")
    object UsersManagerScreen : AppScreens(route = "users_manager")
    object MainMenuScreen : AppScreens(route = "main_menu/{userId}")
    object RegisterScreen : AppScreens(route = "register")
    object ShoppingCartScreen : AppScreens(route = "shopping_cart/{userId}")
    object ModifyUser : AppScreens(route = "modify_user/{userId}")
    object UserLog : AppScreens(route = "user_log/{userId}")
    object ModifyMeal : AppScreens(route = "modify_meal/{mealId}")
    object MealTimeManagerScreen : AppScreens(route = "meal_time")
    object InvoiceManagerScreen : AppScreens(route = "invoice_manager")
    object ModifyInvoice : AppScreens(route = "modify_invoice/{invoiceId}")

}
