package com.example.eduscheduleapp.presentation.authcheck

import com.example.eduscheduleapp.data.remote.dto.LoginData

data class AuthCheckState(
    val isLoading: Boolean = false,
    val loginData : LoginData? = null,
    val error : String = ""
)
