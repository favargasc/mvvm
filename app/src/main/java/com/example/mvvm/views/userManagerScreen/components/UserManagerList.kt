package com.example.mvvm.views.userManagerScreen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.models.Meal
import com.example.mvvm.models.Time
import com.example.mvvm.models.Type
import com.example.mvvm.models.User
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.viewmodels.UserListState
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.FormEntry
import com.example.mvvm.views.mealManagerScreen.components.ComboBoxTime
import com.example.mvvm.views.mealManagerScreen.components.ComboBoxType
import com.example.mvvm.views.mealManagerScreen.components.MealManagerDetail
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterialApi
@Composable
fun UserManagerList(
    state: UserListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    userViewModel: UserViewModel,
    navController: NavController,
) {
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = refreshData) {
        LazyColumn(
            modifier = Modifier.background(Color(0xFFFCFCFC))
        ) {
            items(
                items = state.meals
            ) { user ->
                if (!user.admin) UserManagerDetail(user, userViewModel, navController)
            }
        }
    }
}
