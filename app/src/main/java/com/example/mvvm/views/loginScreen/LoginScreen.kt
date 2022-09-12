package com.example.mvvm.views.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.loginScreen.components.*

enum class Theme { DARK, LIGHT }

enum class Route { CLIENT, ADMIN }

@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
    navController: NavController,
    navigateToMealsManager: () -> Unit,
    userViewModel: UserViewModel
) {
    val (collegeEmail, setCollegeEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Banner(url = "https://bit.ly/3exuRUs")
        Title(text = "Iniciar sesión")

        Spacer(modifier = Modifier.height(30.dp))

        FormEntry("Correo institucional", collegeEmail, setCollegeEmail)
        FormEntry("Contraseña", password, setPassword)


        Spacer(modifier = Modifier.height(35.dp))

        FormBtn(
            "Iniciar sesión como estudiante",
            navController,
            navigateToLogin,
            navigateToMealsManager,
            userViewModel,
            collegeEmail,
            password,
            Theme.DARK.ordinal,
            Route.CLIENT.ordinal
        )

        FormBtn(
            "Iniciar sesión como administrador",
            navController,
            navigateToLogin,
            navigateToMealsManager,
            userViewModel,
            collegeEmail,
            password,
            Theme.LIGHT.ordinal,
            Route.ADMIN.ordinal
        )

        RedirectLink(
            "¿Aún no tienes una cuenta?",
            " Registrarte",
            navigateToRegister,
        )
    }
}
