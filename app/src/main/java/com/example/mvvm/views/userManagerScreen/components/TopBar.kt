package com.example.mvvm.views.userManagerScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun TopBar(
    title: String,
    navigateToLogin: () -> Unit,
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
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(start = 20.dp),
                color = Color(0xFF384A57)
            )

        },
        elevation = 0.dp,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(80.dp),
    )
}