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


    fun getMeals(): Flow<Result<List<Meal>>> = flow {
        try {
            emit(Result.Loading())

            val collection = db.collection("meals")

            val meals = collection.get().await().map { document ->
                document.toObject(Meal::class.java)
            }
            emit(Result.Success(data = meals))

        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }

}