package com.example.mvvm.views.mainMenuScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.MealsIcon
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.views.mainMenuScreen.components.*

val mealsIcons = listOf(
    MealsIcon(0, "Principales", "https://img.icons8.com/glyph-neue/344/rice-bowl.png"),
    MealsIcon(1, "Postres", "https://img.icons8.com/glyph-neue/344/ice-cream-bowl.png"),
    MealsIcon(2, "Adicionales", "https://img.icons8.com/ios-filled/344/taco.png"),
    MealsIcon(3, "Bebidas", "https://img.icons8.com/glyph-neue/344/soda-cup.png"),
)

val mealsIcons2 = listOf(
    MealsIcon(0, "Principales", "https://img.icons8.com/ios-filled/344/0800.png"),
    MealsIcon(1, "Postres", "https://img.icons8.com/ios-filled/344/1100.png"),
    MealsIcon(2, "Adicionales", "https://img.icons8.com/ios-filled/344/1500.png"),
    MealsIcon(3, "Adicionales", "https://img.icons8.com/ios-filled/344/1700.png"),
)

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainMenuScreen(
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    state: MealListState,
    navigateToLogin: () -> Unit,
    navController: NavController,
    orders: SnapshotStateList<CartMeal>,
    userId: String
) {
    val (type, setType) = remember { mutableStateOf(-1) }
    val (time, setTime) = remember { mutableStateOf(-1) }
    val ordersTemp = remember { mutableStateListOf<CartMeal>()}

    Column(
        modifier = Modifier.background(Color(0xFFFCFCFC))
    ) {
        TopBar(
            "Menú",
            navigateToLogin,
            navController,
            userId = userId
        )
        val search=SearchBar()
        OptionSelector(mealsIcons, type, setType)
        OptionSelector(mealsIcons2, time, setTime)
        MealList(
            state,
            isRefreshing,
            refreshData,
            type,
            time,
            ordersTemp,
            search.lowercase()
        )
        OrderBtn("Agregar al carrito", orders, ordersTemp)
    }
}