package com.example.mvvm.repositories

import com.example.mvvm.models.Meal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository
@Inject constructor(
    private val db: FirebaseFirestore
) {
    fun getMeals() : Flow<Result<List<Meal>>> = flow {
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