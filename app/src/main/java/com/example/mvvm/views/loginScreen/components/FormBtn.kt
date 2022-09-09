package com.example.mvvm.views.loginScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvm.navigation.AppScreens

@Composable
fun FormBtn(
    text: String,
    navController: NavController,
    collegeEmail: String,
    password: String,
    style: Int,
    route: Int
    ) {
        Button(
            onClick = {
                val address = if (route == 0 ) AppScreens.MainMenuScreen.route else ""

                navController.navigate(address)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = (
                    if (style == 0) Color(0xFF39439D) else Color.White
                )
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color(0xFF39439D)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 10.dp)
                .height(55.dp)
        ) {
            Text(
                text = text,
                color = (
                    if (style == 0) Color.White else Color(0xFF39439D)
                ),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
}