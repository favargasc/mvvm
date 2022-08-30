package com.example.mvvm.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.UserViewModel

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    val (studentEmail, setStudentEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        LoginImage()
        FormTitle("Iniciar sesión")
        Spacer(modifier = Modifier.height(30.dp))
        FormEntry("Correo institucional", studentEmail, setStudentEmail)
        FormEntry("Contraseña", password, setPassword)

        FormButton("Iniciar sesión como estudiante", navController, AppScreens.MainMenuScreen.route, userViewModel, studentEmail, password)
        LightFormButton("Iniciar sesión como administrador", navController, AppScreens.MainMenuScreen.route)
        Spacer(modifier = Modifier.height(20.dp))
        LoginRedirect("¿Aún no tienes una cuenta?", " Registrate", navController)

    }
}

@Composable
fun LoginImage() {
    Image(
        painter = rememberAsyncImagePainter("https://img.freepik.com/premium-vector/kids-school-lunchroom-sitting-tables-eating-lunch-canteen-space-classes_341509-3376.jpg?w=1380"),
        contentDescription = "",
        modifier = Modifier.padding(20.dp, 5.dp)
    )
}


@Composable
fun LoginRedirect(text: String, link: String, navController: NavController) {
    Row(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        )
    ) {
        Text(
            text = text,
            color = Color(0xFFA0A0A0),
            fontSize = 15.sp
        )

        ClickableText (
            text = AnnotatedString(link),
            style = TextStyle (
                color = Color(0xFF646DB3),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
            ),
            onClick = { navController.navigate(AppScreens.RegisterScreen.route)}
        )
    }
}