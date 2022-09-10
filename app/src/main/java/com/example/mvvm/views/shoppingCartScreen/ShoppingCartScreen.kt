package com.example.mvvm.views.shoppingCartScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import com.example.mvvm.models.CartMeal
import com.example.mvvm.repositories.InvoiceRepository
import com.example.mvvm.viewmodels.CartMealListState
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.shoppingCartScreen.components.*


@SuppressLint("UnrememberedMutableState")
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun ShoppingCartScreen(
    navController: NavController,
    invoiceViewModel: InvoiceViewModel,
    userViewModel: UserViewModel,
    orders: SnapshotStateList<CartMeal>,
    userId: String
) {
    val (mainCost, setMainCost) = remember { mutableStateOf(0.0) }
    val (totalCost, setTotalCost) = remember { mutableStateOf(0.0) }

    Column {
        TopBar(navController, userId)
        OrderList(orders, setMainCost, setTotalCost)
        Payment(mainCost, totalCost)
        PayBtn("Comprar", orders, invoiceViewModel, userViewModel, userId)
    }
}