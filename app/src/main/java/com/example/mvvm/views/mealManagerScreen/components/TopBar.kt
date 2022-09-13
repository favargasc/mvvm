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
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun TopBar(
    title: String,
    modalBottomSheetState: ModalBottomSheetState,
) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(start = 45.dp),
                color = Color(0xFF384A57)
            )
        },
        actions = {
            IconButton(onClick = {
                scope.launch {
                    if (!modalBottomSheetState.isVisible)
                        modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    else
                        modalBottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
                }
            }) {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://cdn-icons-png.flaticon.com/512/3303/3303893.png"),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color(0xFF384A57)
                )
            }
        },
        elevation = 0.dp,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(80.dp),
    )
}