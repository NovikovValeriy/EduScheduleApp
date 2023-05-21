package com.example.eduscheduleapp.data.remote.dto

data class LoginData(
    var access : String,
    val refresh : String,
    val login : String,
    val status : String,
    val student_id : String,
    val group : String,
    val group_id : String,
    val name : String
)
