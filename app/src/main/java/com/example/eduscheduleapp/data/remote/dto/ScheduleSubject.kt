package com.example.eduscheduleapp.data.remote.dto

data class ScheduleSubject(
    val id : Int,
    val day_of_week : String,
    val start_time : String,
    val end_time: String,
    val class_name: String,
    val classroom: Int,
    val schedule: Int
)
