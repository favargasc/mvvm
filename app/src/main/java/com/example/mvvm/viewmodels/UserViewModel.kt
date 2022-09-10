package com.example.mvvm.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.example.mvvm.repositories.Result
import com.example.mvvm.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun addNewUser(user: User) {
        userRepository.addNewUser(user)
    }

    suspend fun authCheck(collegeEmail: String, password: String): Boolean {
        return userRepository.db.collection("users")
            .whereEqualTo("studentEmail", collegeEmail).whereEqualTo("password", password)
            .get()
            .await()
            .isEmpty
    }

    suspend fun getUserId(collegeEmail: String, password: String): String {
        val users = userRepository.db
            .collection("users")
            .whereEqualTo("studentEmail", collegeEmail).whereEqualTo("password", password)
            .get()
            .await()
            .map { document -> document.toObject(User::class.java) }
        return users[0].dsi
    }

    suspend fun getUser(dsi: String): User {
        val users = userRepository.db
            .collection("users")
            .whereEqualTo("dsi", dsi)
            .get()
            .await()
            .map { document -> document.toObject(User::class.java) }
        return users[0]
    }

}