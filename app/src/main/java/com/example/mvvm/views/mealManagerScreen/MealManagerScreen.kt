package com.example.mvvm.views.mealManagerScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.views.mealManagerScreen.components.MealManagerList
import com.example.mvvm.views.mealManagerScreen.components.TopBar

@ExperimentalMaterialApi
@Composable
fun MealsManagerScreen(
    navigateToLogin: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    state: MealListState,
    mealViewModel: MealViewModel,
    navController: NavController
) {
    Column {
        val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

        TopBar("Gestor de Comidas", navigateToLogin, sheetState)
        MealManagerList(
            state,
            isRefreshing,
            refreshData,
            scaffoldState,
            mealViewModel,
            navController
        )
    }
}