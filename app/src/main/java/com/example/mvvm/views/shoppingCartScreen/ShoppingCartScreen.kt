package com.example.mvvm.views.shoppingCartScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import com.example.mvvm.models.CartMeal
import com.example.mvvm.viewmodels.Email
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.shoppingCartScreen.components.OrderList
import com.example.mvvm.views.shoppingCartScreen.components.PayBtn
import com.example.mvvm.views.shoppingCartScreen.components.Payment
import com.example.mvvm.views.shoppingCartScreen.components.TopBar

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedMutableState")
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun ShoppingCartScreen(
    navController: NavController,
    invoiceViewModel: InvoiceViewModel,
    userViewModel: UserViewModel,
    orders: SnapshotStateList<CartMeal>,
    userId: String,
    email: Email
) {
    val (mainCost, setMainCost) = remember { mutableStateOf(0.0) }
    val (totalCost, setTotalCost) = remember { mutableStateOf(0.0) }

    Column {
        TopBar(navController, userId)
        OrderList(orders, setMainCost, setTotalCost)
        Payment(mainCost, totalCost)
        PayBtn(
            "Comprar",
            orders,
            invoiceViewModel,
            userViewModel,
            userId,
            email,
            totalCost,
            mainCost
        )
    }
}