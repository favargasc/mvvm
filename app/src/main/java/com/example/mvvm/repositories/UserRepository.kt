package com.example.mvvm.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mvvm.models.User
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(
    private val userList: CollectionReference
) {
    //funcional
    fun addNewUser(user: User) {
        try {
            userList.document(user.dni).set(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    // falta implementacion
    fun isValidUser(_studentEmail: String, _password: String): Boolean = true
}