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
    val dni = MutableStateFlow("")
    val dsi = MutableStateFlow("")
    val fullName = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    val maidenName = MutableStateFlow("")
    val age = MutableStateFlow(0)
    val date = MutableStateFlow("")
    val studentEmail = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun addNewUser() {
        userRepository.addNewUser(
            User(
                dni.value,
                dsi.value,
                fullName.value,
                lastName.value,
                maidenName.value,
                age.value,
                date.value,
                studentEmail.value,
                password.value
            )
        )
    }
}