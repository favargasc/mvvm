package com.example.mvvm.views.loginScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RedirectLink(
    text: String,
    link: String,
    navigateToRegister: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(
            top = 30.dp,
            bottom = 10.dp
        )
    ) {
        Text(
            text = text,
            color = Color(0xFFA0A0A0),
            fontSize = 16.sp
        )

        ClickableText (
            text = AnnotatedString(link),
            style = TextStyle(
                color = Color(0xFF646DB3),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            onClick = { navigateToRegister() }
        )
    }
}