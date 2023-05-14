package com.example.eduscheduleapp.presentation.authcheck

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.AuthRequest
import com.example.eduscheduleapp.domain.use_case.authorization.AuthorizationUseCase
import com.example.eduscheduleapp.presentation.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthCheckViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    init {
        savedStateHandle.get<String>("login")?.let { login ->
            savedStateHandle.get<String>("password")?.let { password ->
                authorization(AuthRequest(login, password))
            }
        }
    }

    private fun authorization(authRequest : AuthRequest) {
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