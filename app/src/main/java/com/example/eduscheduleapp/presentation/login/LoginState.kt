package com.example.eduscheduleapp.presentation.login

import com.example.eduscheduleapp.data.remote.dto.LoginData

data class LoginState(
    val isLoading: Boolean = false,
    var loginData : LoginData? = null,
    val error : String = ""
)
