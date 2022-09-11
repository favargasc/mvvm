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
<<<<<<< Updated upstream
import com.example.mvvm.repositories.UserRepository
=======
import com.example.mvvm.models.CartMeal
import com.example.mvvm.viewmodels.Email
>>>>>>> Stashed changes
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.*

@ExperimentalUnitApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
<<<<<<< Updated upstream
fun AppNavigation(context: Context, userViewModel: UserViewModel, mealViewModel: MealViewModel) {
=======
fun AppNavigation(
    context: Context,
) {
    val mealViewModel: MealViewModel = hiltViewModel()
    val invoiceViewModel: InvoiceViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()
    val email:Email= hiltViewModel()
    val orders = remember { mutableStateListOf<CartMeal>()
    }
>>>>>>> Stashed changes

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController, userViewModel)
        }
        composable(route = AppScreens.MealsManagerScreen.route) {
            MealsManagerScreen(mealViewModel)
        }
        composable(route = AppScreens.MainMenuScreen.route) {
            MainMenuScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(context, navController, userViewModel, email)
        }

<<<<<<< Updated upstream
        composable(route = AppScreens.ShoppingCartScreen.route) {
            ShoppingCartScreen(navController)
=======
        composable(route = AppScreens.ShoppingCartScreen.route) { backStackEntry ->

            backStackEntry.arguments?.getString("userId")?.let {
                ShoppingCartScreen(
                    navController,
                    invoiceViewModel,
                    userViewModel,
                    orders,
                    it,
                    email
                )
            }
>>>>>>> Stashed changes
        }
    }
}