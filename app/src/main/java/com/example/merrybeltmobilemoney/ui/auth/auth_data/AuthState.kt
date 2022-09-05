package com.example.merrybeltmobilemoney.ui.auth.auth_data

data class AuthState (
    val username: String = "external_user",
    val password: String = "zaq1!QAZ",
    val loadingProgressBar: Boolean = false,
    val isDialogTitle: String = "Notification",
    val isDialogMessage: String = "",
    val isDialogShow: Boolean = false,
)