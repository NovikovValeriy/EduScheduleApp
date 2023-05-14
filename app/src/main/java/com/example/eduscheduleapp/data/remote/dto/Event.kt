package com.example.eduscheduleapp.data.remote.dto

data class Event(
    val id : Int,
    val name : String,
    val photo : String?,
    val description : String,
    var date : String
)
