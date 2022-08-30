package com.example.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mvvm.models.User
import com.example.mvvm.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun addNewUser(user: User) {
        userRepository.addNewUser(user)
    }

    fun isValidUser(studentEmail: String, password: String): Boolean {
        println(userRepository.isValidUser(studentEmail, password))
        return userRepository.isValidUser(studentEmail, password)
    }
}