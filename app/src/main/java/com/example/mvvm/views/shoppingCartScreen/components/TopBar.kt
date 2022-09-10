package com.example.mvvm.views.shoppingCartScreen.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController, userId: String) {
    TopAppBar(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(80.dp),
        title = {
            Box(
                modifier = Modifier
                    .padding(start = 50.dp)
            ) {
                Text(
                    text = "Mi carrito",
                    fontWeight = FontWeight.Light,
                    fontSize = 25.sp
                )
            }
        },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.navigate("main_menu/$userId") }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        elevation = 0.dp
    )
}