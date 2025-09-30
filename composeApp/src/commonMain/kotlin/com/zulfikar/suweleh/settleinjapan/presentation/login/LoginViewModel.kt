package com.zulfikar.suweleh.settleinjapan.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginRequest
import com.zulfikar.suweleh.settleinjapan.data.remote.dto.LoginResponse
import com.zulfikar.suweleh.settleinjapan.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// A sealed interface to represent the different states of the login UI
sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    data class Success(val loginResponse: LoginResponse) : LoginUiState
    data class SuccessGreeting(val response: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
}

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val request = LoginRequest(username = _username.value, password = _password.value)
            loginUseCase(request)
                .onSuccess { response ->
                    _uiState.value = LoginUiState.Success(response)
                }
                .onFailure { error ->
                    _uiState.value =
                        LoginUiState.Error(error.message ?: "An unknown error occurred")
                }
        }
    }
}
