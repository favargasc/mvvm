package com.example.mvvm.repositories

import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(
    val db: FirebaseFirestore
) {
    fun addNewUser(user: User) {
        try {
            val userList = db.collection("users")

            userList.document(user.dni).set(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUsers(): Flow<Result<List<User>>> = flow {
        try {
            emit(Result.Loading())

            val collection = db.collection("users")

            val users = collection.get().await().map { document ->
                document.toObject(User::class.java)
            }
            emit(Result.Success(data = users))

        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }

    fun removeUser(user: User) {
        db.collection("users").document(user.studentEmail).delete()
    }

    fun modifyUser(user: User, userId: String) {
        val userRef = db.collection("users").document(userId)

        if (user.studentEmail != "") {
            userRef.update("studentEmail", user.studentEmail)
        }
        if (user.password != "") {
            userRef.update("password", user.password)
        }
        if (user.maidenName != "") {
            userRef.update("maidenName", user.maidenName)
        }
        if (user.lastName != "") {
            userRef.update("lastName", user.lastName)
        }
        if (user.fullName != "") {
            userRef.update("fullName", user.fullName)
        }
        if (user.dsi != "") {
            userRef.update("dsi", user.dsi)
        }
        if (user.dni != "") {
            userRef.update("dni", user.dni)
        }
        if (user.date != "") {
            userRef.update("date", user.date)
        }
        if (user.age != 0) {
            userRef.update("age", user.age)
        }
    }
}