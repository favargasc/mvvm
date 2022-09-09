package com.example.mvvm.views.loginScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Banner(url: String) {
    Image(
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = "",
        modifier = Modifier.padding(20.dp, 5.dp)
    )
}