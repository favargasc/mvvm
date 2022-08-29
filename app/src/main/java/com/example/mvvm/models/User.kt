package com.example.mvvm.models

import java.util.*

open class User(
    val id: String,
    val email: String,
    val password: String,
)

class Client(
    id: String,
    email: String,
    password: String,
    val IDNumber: String,
    val studentID: String,
    val firstSurname: String,
    val secondSurname: String,
    val age: Int,
    val birthdate: Date
) : User(id, email, password)

class Admin(
    id: String,
    email: String,
    password: String
) : User(id, email, password)