package com.example.mvvm.repositories

import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Invoice
import com.example.mvvm.models.Meal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoiceRepository
@Inject constructor(
    private val db: FirebaseFirestore
) {
    fun addNewInvoice(invoice: Invoice) {
        try {
            val userList = db.collection("invoices")

            userList.document(invoice.id).set(invoice)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    /*fun getOrders() : Flow<Result<List<CartMeal>>> = flow {
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
*/
}