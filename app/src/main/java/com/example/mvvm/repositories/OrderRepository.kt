package com.example.mvvm.repositories

import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Meal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository
@Inject constructor(
    private val db: FirebaseFirestore
) {
    fun addNewOrder(cartMeal: CartMeal) {
        try {
            val userList = db.collection("orders")

            userList.document(cartMeal.id).set(cartMeal)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getOrders() : Flow<Result<List<CartMeal>>> = flow {
        try {
            emit(Result.Loading())

            val collection = db.collection("orders").whereEqualTo("userId", "117890261")

            val meals = collection.get().await().map { document ->
                document.toObject(CartMeal::class.java)
            }
            emit(Result.Success(data = meals))

        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }

}