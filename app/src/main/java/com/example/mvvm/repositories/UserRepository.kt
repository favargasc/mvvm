package com.example.mvvm.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mvvm.di.UserList
import com.example.mvvm.models.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(
    private val db: FirebaseFirestore
) {
    //funcional
    fun addNewUser(user: User) {
        try {
            val userList = db.collection("users")

            userList.document(user.dni).set(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
/*
    suspend fun getUsers(): MutableList<User> {
        val list = mutableListOf<User>()

        userList
            .get()
            .addOnSuccessListener {users ->
                for (user in users) {
                    list.add(user.toObject(User::class.java))
                }

            }
        return list
    }*/
}