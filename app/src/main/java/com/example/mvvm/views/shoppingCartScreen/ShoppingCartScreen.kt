package com.example.mvvm.views.shoppingCartScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Meal
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.CartMealListState
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.OrderViewModel
import com.example.mvvm.views.shoppingCartScreen.components.*


@SuppressLint("UnrememberedMutableState")
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun ShoppingCartScreen(
    state: CartMealListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    navigateToMainMenu: () -> Unit,
    orders: SnapshotStateList<CartMeal>
) {
    val (mainCost, setMainCost) = remember { mutableStateOf(0.0) }
    val (totalCost, setTotalCost) = remember { mutableStateOf(0.0) }

    Column {
        TopBar(navigateToMainMenu)
        OrderList(orders, isRefreshing, setMainCost, setTotalCost)
        Payment(state, mainCost, totalCost)
    }
}