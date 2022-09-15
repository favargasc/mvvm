package com.example.mvvm.views.mealManagerScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.views.mealManagerScreen.components.BottomBar
import com.example.mvvm.views.mealManagerScreen.components.MealManagerList
import com.example.mvvm.views.mealManagerScreen.components.TopBar

@ExperimentalMaterialApi
@Composable
fun MealsManagerScreen(
    navigateToLogin: () -> Unit,
    navigateToUserManager: () -> Unit,
    navigateToMealTimeManager: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    state: MealListState,
    mealViewModel: MealViewModel,
    navController: NavController
) {

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    Scaffold(
        topBar = { TopBar("Gestor de Comidas", modalBottomSheetState) },
        bottomBar = { BottomBar(navigateToLogin, navigateToUserManager, navigateToMealTimeManager) }
    ) {
        Column {
            MealManagerList(
                state,
                isRefreshing,
                refreshData,
                modalBottomSheetState,
                mealViewModel,
                navController
            )
        }
    }
}