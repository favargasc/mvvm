package com.example.mvvm.repositories

import com.example.mvvm.models.User
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(
    private val userList: CollectionReference // entidad
) {
    fun addNewUser(user: User) {
        try {
            userList.document(user.dni).set(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*fun getUsers(id: String): User? {
        var user: User? = null

        userList
            .document(id)
            .get()
            .addOnSuccessListener {
                user = Admin(
                    it.get("id") as String,
                    it.get("username") as String,
                    it.get("password") as String,
                )
            }
        return user
    }*/
}