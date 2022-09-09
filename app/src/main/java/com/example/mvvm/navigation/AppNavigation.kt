package com.example.mvvm.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm.models.CartMeal
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.viewmodels.OrderViewModel
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.*
import com.example.mvvm.views.loginScreen.LoginScreen
import com.example.mvvm.views.mainMenuScreen.MainMenuScreen
import com.example.mvvm.views.shoppingCartScreen.ShoppingCartScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@ExperimentalUnitApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    context: Context,
    userViewModel: UserViewModel,
) {
    val mealViewModel: MealViewModel = hiltViewModel()
    val orderViewModel: OrderViewModel = hiltViewModel()
    val orders = remember { mutableStateListOf<CartMeal>()}

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainMenuScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.MealsManagerScreen.route) {
            MealsManagerScreen()
        }
        composable(route = AppScreens.MainMenuScreen.route) {
            val state = mealViewModel.state.value
            val isRefreshing = mealViewModel.isRefreshing.collectAsState()

            MainMenuScreen(
                state = state,
                isRefreshing = isRefreshing.value,
                refreshData = mealViewModel::getMealList,
                navigateToLogin = { navController.navigate(AppScreens.LoginScreen.route) },
                navigateToShoppingCart = { navController.navigate(AppScreens.ShoppingCartScreen.route) },
                orderViewModel = orderViewModel,
                orders = orders
            )
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(context, navController, userViewModel)
        }

        composable(route = AppScreens.ShoppingCartScreen.route) {
            val state = orderViewModel.state.value
            val isRefreshing = orderViewModel.isRefreshing.collectAsState()

            ShoppingCartScreen(
                state = state,
                isRefreshing = isRefreshing.value,
                refreshData = orderViewModel::getCartMealList,
                navigateToMainMenu = { navController.navigate(AppScreens.MainMenuScreen.route) },
                orders = orders
            )
        }
    }
}