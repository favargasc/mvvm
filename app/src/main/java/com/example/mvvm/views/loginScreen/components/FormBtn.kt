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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun FormBtn(
    text: String,
    navController: NavController,
    navigateToLogin: () -> Unit,
    navigateToMealManager: () -> Unit,
    userViewModel: UserViewModel,
    collegeEmail: String,
    password: String,
    style: Int,
    route: Int
) {
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            if (route == 0) {
                coroutineScope.launch {
                    val response = userViewModel.authCheck(collegeEmail, password)
                    if (!response) {
                        val userId = userViewModel.getUserId(collegeEmail, password)

                        navController.navigate("main_menu/$userId")

                    } else {
                        navigateToLogin()
                    }
                }
            } else {
                navigateToMealManager()
            }
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = (
                    if (style == 0) Color(0xFF39439D) else Color.White
                    )
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFF39439D)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 5.dp, bottom = 10.dp)
            .height(60.dp)
    ) {
            Text(
                text = text,
                color = (
                        if (style == 0) Color.White else Color(0xFF39439D)
                        ),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
}