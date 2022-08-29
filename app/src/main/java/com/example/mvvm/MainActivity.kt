package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvm.navigation.AppNavigation
import com.example.mvvm.ui.theme.MvvmTheme
import com.example.mvvm.viewmodels.UserViewModel
import com.example.mvvm.views.LoginScreen
import com.example.mvvm.views.MealsManagerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(this)
                }
            }
        }
    }
}

@Composable
fun AddNewUser() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val userViewModel: UserViewModel = hiltViewModel()

    Column {
        TextField(value = username, onValueChange = { username = it })
        TextField(value = password, onValueChange = { password = it })
        Button(onClick = {
            userViewModel.addNewUser(username, password)
        }) {
            Text("Save")
        }
    }
}