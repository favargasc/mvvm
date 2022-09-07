package com.example.mvvm.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mvvm.models.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(
    private val userList: CollectionReference,
    private val db: FirebaseFirestore = Firebase.firestore
) {
    fun addNewUser(user: User) {
        try {
            userList.document(user.studentEmail).set(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //Check if the user exist in firebase
    //return true if the email exist
    suspend fun isUserRegistered(user:User):Boolean{
        val request=db.collection("users").whereEqualTo("studentEmail", user.studentEmail)
            .get()
        val result=request.await()
        return result.isEmpty
    }

    //check if the email is ¨@estudiantec¨
    fun checkEmail(user:User):Boolean{
        if(user.studentEmail.lowercase().contains("@estudiantec.cr")) return true
        return false
    }

    //return all the users
    suspend fun getUsers(): MutableList<User> {
        var users = mutableListOf<User>()
        val request=db.collection("users").get()
        var result=request.await()
        for (document in result) {
            val user = document.toObject(User::class.java)
            users.add(user)
        }
        return users
    }



    fun deleteUser(user:User){
        db.collection("users").document(user.studentEmail).delete().addOnSuccessListener(){
            Log.d(TAG, "User deleted")
        }
    }

    fun modifyUser(user:User){
        db.collection("users").document(user.studentEmail).set(user).addOnSuccessListener(){
            Log.d(TAG, "User modified")
        }
    }


    //verify if the user exists
    //returns the student id
    //if the user don´t exist, return ¨Error¨
    suspend fun isValidUser(_studentEmail: String, _password: String): String {
        val request=db.collection("users").whereEqualTo("studentEmail",_studentEmail)
            .whereEqualTo("password",_password).get()
        val result=request.await()
        //verify if the user exist
        if (result.isEmpty) return "Error"
        var user=User()
        //Not allow to do the toObject without the loop
        for (document in result) {
             user= document.toObject(User::class.java)
        }
        return user.studentEmail
    }

    ///verificar si es admin

}