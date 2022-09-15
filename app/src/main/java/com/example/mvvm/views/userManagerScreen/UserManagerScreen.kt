package com.example.mvvm.views.userManagerScreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mvvm.viewmodels.UserListState
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.userManagerScreen.components.TopBar
import com.example.mvvm.views.userManagerScreen.components.UserManagerList

@ExperimentalMaterialApi
@Composable
fun UserManagerScreen(
    navigateToMealManager: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    state: UserListState,
    userViewModel: UserViewModel,
    navController: NavController
) {
    Column {
        TopBar("Gestor de Usuarios", navigateToMealManager)
        UserManagerList(state, isRefreshing, refreshData, userViewModel, navController)
    }
}