package com.example.mvvm.views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.mvvm.models.User
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.time.Period
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(context: Context, navController: NavController, userViewModel: UserViewModel) {
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
        TopAppBar(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(80.dp),
            title = {
                FormTitle("Registrarse")
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.LoginScreen.route) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            },
            elevation = 0.dp
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormEntry("Número de cédula", dni, setDni)
        FormEntry("Número de carné", dsi, setDsi)
        FormEntry("Nombre", fullName, setFullName)
        FormEntry("Primer apellido", lastName, setLastName)
        FormEntry("Segundo apellido", maidenName, setMaidenName)
        FormEntry("Fecha de nacimiento", context, date, setDate, age, setAge)
        FormEntry("Correo institucional", studentEmail, setStudentEmail)
        FormEntry("Contraseña", password, setPassword)
        FormButton(
            "Crear cuenta",
            navController,
            AppScreens.LoginScreen.route,
            userViewModel,
            User(dni, dsi, fullName, lastName, maidenName, age, date, studentEmail, password)
        )
    }
}

@Composable
fun FormEntry(labelText: String, state: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        FormLabel(labelText)
        BlueInput(state, onValueChange)
    }
}

@Composable
fun FormEntry(age: Int, labelText: String) {
    Column(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        FormLabel(labelText)
        BlueInput(age)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormEntry(labelText: String, context: Context, date: String, onDateChange: (String) -> Unit, age: Int, onAgeChange: (Int) -> Unit) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        FormLabel(labelText)
        DatePickerForm(context, date, onDateChange, age, onAgeChange)
    }
}

@Composable
fun FormTitle(text: String) {
    Text(
        text = text,
        color = Color(0xFF3F49A0),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        modifier = Modifier.padding(start = 30.dp)
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BlueInput(state: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = state,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF0F5FE),
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color(0xFFF1F1FE)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(52.dp)
    )
}

@Composable
fun BlueInput(age: Int) {
    OutlinedTextField(
        value = age.toString(),
        enabled = false,
        onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF0F5FE),
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color(0xFFF1F1FE)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(52.dp)
    )
}

@Composable
fun FormLabel(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(4.dp)
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerForm(context: Context, date: String, onDateChange: (String) -> Unit, age: Int, onAgeChange: (Int) -> Unit) {
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        {
            _: DatePicker,
            y: Int,
            m: Int,
            dayOfMonth: Int ->
                onDateChange("$dayOfMonth/${m + 1}/$y")
                onAgeChange(setAge(dayOfMonth, m, y))

        }, year, month, day
    )
    OutlinedTextField(
        value = date,
        enabled = false,
        onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF1F1FE),
            focusedBorderColor = Color(0xFF39439D),
            unfocusedBorderColor = Color(0xFFF1F1FE)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(52.dp)
            .clickable(onClick = { datePickerDialog.show() })
    )
    FormEntry(age, "Edad")
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
fun setAge(day: Int, month: Int, year: Int): Int {
    val now: LocalDate = LocalDate.now()
    val birthday: LocalDate = LocalDate.of(year, month, day)
    return Period.between(birthday, now).years
}

@Composable
fun FormButton(text: String, navController: NavController, route: String, userViewModel: UserViewModel, user: User) {
    Button(
        onClick = {
            if (user.studentEmail.contains("@estudiantec.cr") || user.studentEmail.contains("@itcr.ac.cr")) {
                userViewModel.addNewUser(user)
                navController.navigate(route = route)
            } else {
                navController.navigate(route = AppScreens.LoginScreen.route)
            }

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