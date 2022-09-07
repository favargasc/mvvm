package com.example.mvvm.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mvvm.models.User
import com.example.mvvm.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import javax.inject.Inject
import javax.security.auth.callback.Callback

@HiltViewModel
class UserViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun addNewUser(user: User) {
        userRepository.addNewUser(user)
    }

     fun getUsers(callback: (MutableList<User>)->Unit) {
         var users= mutableListOf<User>()
          CoroutineScope(Dispatchers.Main).async{
              users=userRepository.getUsers()
              callback(users)
         }
    }


    fun deleteUser(user:User){
        userRepository.deleteUser(user)
    }

    fun modifyUser(user:User){
        userRepository.modifyUser(user)
    }

    //login
    //verify if the user exists
    //returns the student id
    //if the user don´t exist, return ¨Error¨
    fun isValidUser(studentEmail: String, password: String, callback: (String)->Unit) {
        CoroutineScope(Dispatchers.Main).async{
            val user=userRepository.isValidUser(password, studentEmail)
            callback(user)
        }
    }

    //Check if the user exist in firebase
    //true if the email exist
    fun isUserRegistered( user:User,callback: (Boolean)->Unit) {
        CoroutineScope(Dispatchers.Main).async{
            val isRegistered=userRepository.isUserRegistered(user)
            callback(isRegistered)
        }
    }

    //Check if the email is "@estudiantec.cr
    fun checkEmail(user:User):Boolean{
        return userRepository.checkEmail(user)
    }

}
