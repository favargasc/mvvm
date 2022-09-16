package com.example.mvvm.views.invoicesManagerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.viewmodels.InvoicesListState
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.views.invoicesManagerScreen.components.InvoiceDetail
import com.example.mvvm.views.mainMenuScreen.components.SearchBar
import com.example.mvvm.views.mealManagerScreen.components.MealManagerDetail
import com.example.mvvm.views.userManagerScreen.components.TopBar
import com.example.mvvm.views.userManagerScreen.components.UserLogDetail
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterialApi
@Composable
fun InvoiceManagerScreen(
    navigateToMealManager: () -> Unit,
    refreshData: () -> Unit,
    state: InvoicesListState,
    isRefreshing: Boolean,
    invoiceViewModel: InvoiceViewModel,
    navController: NavController

) {
    val (search, setSearch) = remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar("Gestor de Pedidos", navigateToMealManager) }
    ) {
        Column {
            SearchBar(search, setSearch)

            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = refreshData) {
                LazyColumn(
                    modifier = Modifier
                        .height(580.dp)
                        .background(Color(0xFFFCFCFC)),
                ) {
                    items(
                        items = state.meals
                    ) { invoice ->
                        if (invoice.id.lowercase().contains(search)) InvoiceDetail(
                            invoice,
                            invoiceViewModel,
                            navController
                        )
                    }
                }
            }
        }
    }
}