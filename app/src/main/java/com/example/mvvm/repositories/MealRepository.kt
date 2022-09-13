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

    fun removeMeal(meal: Meal) {
        db.collection("meals").document(meal.id).delete()
    }

    fun addNewMeal(meal: Meal) {
        db.collection("meals").document(meal.id).set(meal)
    }

    fun modifyMeal(meal: Meal, mealId: String) {
        val mealRef = db.collection("meals").document(mealId)

        if (meal.name != "") {
            mealRef.update("name", meal.name)
        }
        if (meal.cost != 0.0) {
            mealRef.update("cost", meal.cost)
        }
        if (meal.img != "") {
            mealRef.update("img", meal.img)
        }
        mealRef.update("availability", meal.availability)
        mealRef.update("type", meal.type)
        mealRef.update("time", meal.time)

    }
}