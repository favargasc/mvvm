package com.example.mvvm.views.mainMenuScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopBar(
    title: String,
    navigateToLogin: () -> Unit,
    navigateToShoppingCart: () -> Unit

) {
    TopAppBar(
        backgroundColor = Color(0xFFFCFCFC),
        navigationIcon = {
            IconButton(onClick = navigateToLogin) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        title = {
            Box(
                modifier = Modifier
                    .padding(start = 70.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Light,
                    fontSize = 25.sp
                )
            }

        },
        actions = {
            IconButton(onClick = navigateToShoppingCart) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color(0xFF000000)
                )
            }
        },
        elevation = 0.dp,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(80.dp),
    )
}