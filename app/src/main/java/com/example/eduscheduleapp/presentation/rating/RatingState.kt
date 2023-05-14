package com.example.eduscheduleapp.presentation.rating

import com.example.eduscheduleapp.data.remote.dto.Student

data class RatingState(
    val isLoading: Boolean = false,
    val students: List<Student> = emptyList(),
    val error: String = ""
)
