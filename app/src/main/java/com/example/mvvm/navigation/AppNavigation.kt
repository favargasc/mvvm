package com.example.mvvm.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm.repositories.UserRepository
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.*

@ExperimentalUnitApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(context: Context, userViewModel: UserViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.MealsManagerScreen.route) {
            MealsManagerScreen()
        }
        composable(route = AppScreens.MainMenuScreen.route) {
            MainMenuScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(context, navController, userViewModel)
        }

        composable(route = AppScreens.ShoppingCartScreen.route) {
            ShoppingCartScreen(navController)
        }
    }
}