package com.example.mvvm.views.userManagerScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvm.models.User
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.FormButton
import com.example.mvvm.views.FormEntry
import com.example.mvvm.views.FormTitle
import com.example.mvvm.views.userManagerScreen.components.TopBar
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun ModifyUser(
    userViewModel: UserViewModel,
    navigateToUserManager: () -> Unit,
    context: Context,
    userId: String
) {
    val (dni, setDni) = remember { mutableStateOf("") }
    val (dsi, setDsi) = remember { mutableStateOf("") }
    val (fullName, setFullName) = remember { mutableStateOf("") }
    val (lastName, setLastName) = remember { mutableStateOf("") }
    val (maidenName, setMaidenName) = remember { mutableStateOf("") }
    val (age, setAge) = remember { mutableStateOf(0) }
    val (date, setDate) = remember { mutableStateOf("") }
    val (studentEmail, setStudentEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        TopBar("Editor de Usuario", navigateToUserManager)
        Spacer(modifier = Modifier.height(20.dp))
        FormEntry("Número de cédula", dni, setDni)
        FormEntry("Número de carné", dsi, setDsi)
        FormEntry("Nombre", fullName, setFullName)
        FormEntry("Primer apellido", lastName, setLastName)
        FormEntry("Segundo apellido", maidenName, setMaidenName)
        FormEntry("Fecha de nacimiento", context, date, setDate, age, setAge)
        FormEntry("Correo institucional", studentEmail, setStudentEmail)
        FormEntry("Contraseña", password, setPassword)

        val user =
            User(dni, dsi, fullName, lastName, maidenName, age, date, studentEmail, password, false)
        FormButton("Modificar Usuario", userViewModel, user, userId)
    }
}

@Composable
fun FormButton(text: String, userViewModel: UserViewModel, user: User, userId: String) {
    Button(
        onClick = {
            userViewModel.modifyUser(user, userId)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF39439D)
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 40.dp,
                end = 40.dp,
                top = 35.dp,
                bottom = 10.dp
            )
            .height(55.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}