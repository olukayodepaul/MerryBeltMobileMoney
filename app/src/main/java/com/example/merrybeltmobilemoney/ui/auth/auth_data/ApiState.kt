package com.example.merrybeltmobilemoney.ui.auth.auth_data

sealed class LoginAuthState {
    object Loading : LoginAuthState()
    data class Success(val status: Int) : LoginAuthState()
    data class Error(val error: String) : LoginAuthState()
}