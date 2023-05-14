package com.example.eduscheduleapp.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.AuthRequest
import com.example.eduscheduleapp.domain.use_case.authorization.AuthorizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

//    init {
//        authorization()
//    }

    fun authorization(authRequest : AuthRequest) {
        authorizationUseCase(authRequest).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LoginState(loginData = result.data)
                    Log.d("POPA", "no error")
                }
                is Resource.Error -> {
                    _state.value = LoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                    Log.d("POPA", _state.value.error)
                }
                is Resource.Loading -> {
                    Log.d("POPA", "loading")
                    _state.value = LoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}