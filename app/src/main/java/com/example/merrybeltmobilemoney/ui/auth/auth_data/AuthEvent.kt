package com.example.merrybeltmobilemoney.ui.auth.auth_data

sealed class AuthEvent{
    class ChangeUserName(val username: String): AuthEvent()
    class ChangeUserPassword(val passwords: String): AuthEvent()
    class ChangeUserProgressBar(val loader: Boolean): AuthEvent()
    class ShowUserDialog(val message: String? = "", val viewStatus: Boolean? = false): AuthEvent()
}


