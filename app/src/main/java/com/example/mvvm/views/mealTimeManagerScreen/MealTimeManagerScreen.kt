package com.example.mvvm.views.mealTimeManagerScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.viewmodels.UserListState
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.mainMenuScreen.components.SearchBar
import com.example.mvvm.views.mealManagerScreen.components.BottomBar
import com.example.mvvm.views.mealManagerScreen.components.MealManagerDetail
import com.example.mvvm.views.mealManagerScreen.components.MealManagerList
import com.example.mvvm.views.userManagerScreen.components.TopBar
import com.example.mvvm.views.userManagerScreen.components.UserManagerList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterialApi
@Composable
fun MealTimeManagerScreen(
    navigateToMealManager: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    state: MealListState,
    mealViewModel: MealViewModel,
) {
    Scaffold(
        topBar = { TopBar("Gestor de tiempos", navigateToMealManager) }
    ) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = refreshData) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFCFCFC)),
            ) {
                items(
                    items = state.meals
                ) { meal ->
                    MealTimeManagerDetail(meal, mealViewModel)
                }
            }

        }
    }
}
