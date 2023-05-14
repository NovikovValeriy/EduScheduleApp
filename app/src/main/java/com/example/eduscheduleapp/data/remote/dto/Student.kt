package com.example.eduscheduleapp.data.remote.dto

data class Student(
    val id: Int,
    val group_name: String,
    val name: String,
    val marks: List<Mark>,
    var GPA: Double
)
