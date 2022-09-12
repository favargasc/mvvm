package com.example.mvvm.views.mainMenuScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mvvm.models.CartMeal
import com.example.mvvm.viewmodels.MealListState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.*

@Composable
fun MealList(
    state: MealListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    type: Int,
    time: Int,
    orders: SnapshotStateList<CartMeal>,
    search: String
) {
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = refreshData) {
        LazyColumn(
            modifier = Modifier
                .height(380.dp)
                .background(Color(0xFFFCFCFC)),
        ) {
            items(
                items = state.meals
            ) { meal ->

                val (count, setCount) = rememberSaveable { mutableStateOf(0) }

                if (time == -1 && type == -1 && meal.name.lowercase().contains(search)) {
                    ProductDetail(meal, count, setCount)
                }
                if (meal.type == type && meal.time == time && meal.name.lowercase().contains(search)) {
                    ProductDetail(meal, count, setCount)
                }
                if (time == -1 && meal.name.lowercase().contains(search)) {
                    if (meal.type == type && meal.name.lowercase().contains(search)) {
                        ProductDetail(meal, count, setCount)
                    }
                }
                if (type == -1 && meal.name.lowercase().contains(search)) {
                    if (meal.time == time) {
                        ProductDetail(meal, count, setCount)
                    }
                }
                val cartMeal: CartMeal? = orders.find { it.meal?.ID == meal.ID }

                val id: String = UUID.randomUUID().toString().replace("-", "").removeRange(8, 32)

                if (cartMeal != null) {
                    if (cartMeal.count < count && count != 0) {
                        orders.remove(cartMeal)
                        orders.add(CartMeal(id, meal, count))
                    }
                } else {
                    if (count != 0) {
                        orders.add(CartMeal(id, meal, count))
                    }
                }
            }
        }
    }
}