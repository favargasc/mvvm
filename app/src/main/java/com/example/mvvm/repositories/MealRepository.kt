package com.example.mvvm.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MealRepository
@Inject constructor(
    private val mealList: CollectionReference,
    private val db: FirebaseFirestore = Firebase.firestore
) {
    fun addNewMeal(meal: Meal) {
        db.collection("meal").document(meal.ID).set(meal).addOnSuccessListener {
            Log.d(TAG, "Successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun deleteMeal(meal: Meal){
        db.collection("meal").document(meal.ID).delete()
    }

    fun modifyMeal(meal: Meal){
        db.collection("meal").document(meal.ID).set(meal).addOnSuccessListener{
            Log.d(TAG, "Successfully written!") }
        .addOnFailureListener { e -> Log.w(TAG, "Error modifying document", e) }
    }

    //get all the meals
    suspend fun getMeals() : MutableList<Meal>{
        var meals = mutableListOf<Meal>()
        val request=db.collection("meal").get()
        var result=request.await()
        for (document in result) {
            val meal = document.toObject(Meal::class.java)
            meals.add(meal)
        }
        return meals
    }

    //verify if the meal exists in Firebase
    suspend fun verifyMeal(name:String):Boolean{
        val request=db.collection("meal").whereEqualTo("name",name).get()
        val result=request.await()
        //verify if the user exist
        if (result.isEmpty) return true

        return false

    }
}