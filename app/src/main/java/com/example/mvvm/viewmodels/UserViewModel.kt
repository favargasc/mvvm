package com.example.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mvvm.models.User
import com.example.mvvm.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

//repositorio  <== viewmodel <== view
@HiltViewModel
class UserViewModel
@Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    fun addNewUser(username: String, password: String) {
        val user = User(
            UUID.randomUUID().toString(),
            username,
            password
        )
        userRepository.addNewUser(user)
    }
}