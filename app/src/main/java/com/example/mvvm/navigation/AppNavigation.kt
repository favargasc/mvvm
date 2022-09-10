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
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.*
import com.example.mvvm.views.loginScreen.LoginScreen
import com.example.mvvm.views.mainMenuScreen.MainMenuScreen
import com.example.mvvm.views.shoppingCartScreen.ShoppingCartScreen

@ExperimentalUnitApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    context: Context,
) {
    val mealViewModel: MealViewModel = hiltViewModel()
    val invoiceViewModel: InvoiceViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()
    val orders = remember { mutableStateListOf<CartMeal>() }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                navigateToRegister = { navController.navigate(AppScreens.RegisterScreen.route) },
                navigateToLogin = { navController.navigate(AppScreens.LoginScreen.route) },
                navController = navController,
                navigateToAdminMainMenu = { navController.navigate(AppScreens.ShoppingCartScreen.route) },
                userViewModel = userViewModel
            )
        }
        composable(route = AppScreens.MealsManagerScreen.route) {
            MealsManagerScreen()
        }
        composable(
            route = AppScreens.MainMenuScreen.route
        ) { backStackEntry ->

            val state = mealViewModel.state.value
            val isRefreshing = mealViewModel.isRefreshing.collectAsState()

            backStackEntry.arguments?.getString("userId")?.let {
                MainMenuScreen(
                    state = state,
                    isRefreshing = isRefreshing.value,
                    refreshData = mealViewModel::getMealList,
                    navigateToLogin = { navController.navigate(AppScreens.LoginScreen.route) },
                    navController = navController,
                    orders = orders,
                    userId = it
                )
            }
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(context, navController, userViewModel)
        }

        composable(route = AppScreens.ShoppingCartScreen.route) { backStackEntry ->

            backStackEntry.arguments?.getString("userId")?.let {
                ShoppingCartScreen(
                    navController,
                    invoiceViewModel,
                    userViewModel,
                    orders,
                    it
                )
            }
        }
    }
}