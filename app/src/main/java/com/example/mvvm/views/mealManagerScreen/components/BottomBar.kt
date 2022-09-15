package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun BottomBar(
    navigateToLogin: () -> Unit,
    navigateToUserManager: () -> Unit,
    navigateToMealTimeManager: () -> Unit,
) {
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://cdn-icons-png.flaticon.com/512/8066/8066409.png"),
                    contentDescription = "",
                    tint = Color(0xFF384A57),
                    modifier = Modifier
                        .size(30.dp)
                )
            },

            onClick = navigateToLogin,
            selected = true
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://cdn-icons-png.flaticon.com/512/1077/1077063.png"),
                    contentDescription = "",
                    tint = Color(0xFF384A57),
                    modifier = Modifier
                        .size(30.dp)
                )
            },
            onClick = navigateToUserManager,
            selected = true
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://cdn-icons-png.flaticon.com/512/992/992700.png"),
                    contentDescription = "",
                    tint = Color(0xFF384A57),
                    modifier = Modifier
                        .size(30.dp)
                )
            },
            onClick = navigateToMealTimeManager,
            selected = true
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://cdn-icons-png.flaticon.com/512/711/711894.png"),
                    contentDescription = "",
                    tint = Color(0xFF384A57),
                    modifier = Modifier
                        .size(30.dp)
                )
            },
            onClick = {},
            selected = true
        )
    }
}