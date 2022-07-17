package com.example.merrybeltmobilemoney.ui.auth.auth_data

data class AuthState (
    val username: String = "",
    val password: String = "",
    val loadingProgressBar: Boolean = false,
    val isDialogTitle: String = "Notification",
    val isDialogMessage: String = "",
    val isDialogShow: Boolean = false,
)