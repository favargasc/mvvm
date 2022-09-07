package com.example.mvvm

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.mvvm.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.example.mvvm.viewmodels.MealViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvm.navigation.AppNavigation
import com.example.mvvm.ui.theme.MvvmTheme




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()


    @SuppressLint("CoroutineCreationDuringComposition")
    @ExperimentalUnitApi
    @ExperimentalMaterialApi
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmTheme {
                userViewModel.getUsers()  { user->
                    Log.d(TAG, "Usuarios: ${user.toString()}")
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(
                        this,
                        userViewModel,
                        mealViewModel
                    )
                }
            }
        }
    }
}