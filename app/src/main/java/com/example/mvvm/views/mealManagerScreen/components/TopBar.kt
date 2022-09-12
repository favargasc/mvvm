package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun TopBar(
    title: String,
    navigateToLogin: () -> Unit,
    sheetState: BottomSheetState
) {
    val scope = rememberCoroutineScope()
    var showMenu1 by remember { mutableStateOf(false) }

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
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
            }

        },
        actions = {
            IconButton(onClick = {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        if (showMenu1) showMenu1 = false
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }

                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
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