package com.example.mvvm.views.userManagerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mvvm.viewmodels.InvoicesListState
import com.example.mvvm.viewmodels.UserListState
import com.example.mvvm.views.userManagerScreen.components.TopBar
import com.example.mvvm.views.userManagerScreen.components.UserLogDetail

@ExperimentalMaterialApi
@Composable
fun UserLog(
    navigateToUserManager: () -> Unit,
    state: InvoicesListState,
    userId: String
) {
    Column {
        TopBar("Historial de Compras", navigateToUserManager)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F7F9)),
        ) {
            items(
                items = state.meals
            ) { invoice ->
                if (invoice.user?.studentEmail == userId) UserLogDetail(invoice)
            }
        }
    }
}