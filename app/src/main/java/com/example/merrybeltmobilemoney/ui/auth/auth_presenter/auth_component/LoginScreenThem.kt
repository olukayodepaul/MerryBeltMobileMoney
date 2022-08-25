package com.example.merrybeltmobilemoney.ui.auth.auth_presenter.auth_component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.auth.auth_data.AuthEvent
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginAuthState
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel


@Composable
fun LoginScreenTheme(
    onClick:()->Unit,
    viewModel: AuthViewModel
    ){
    MaterialTheme{

        val loginSate = viewModel.uiState.collectAsState().value
        val handleEvent = viewModel::authEventHandler

        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = White
                )
        ) {

            ImageLogo()

            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .padding(top = 80.dp)

            ) {
                InputForms(
                    username = loginSate.username,
                    onValueChange = {username->
                        handleEvent(
                            AuthEvent.ChangeUserName(username)
                        )
                    },
                    label = "Username",
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                PasswordFields (
                    username = loginSate.password,
                    onValueChange = {password->
                        handleEvent(
                            AuthEvent.ChangeUserPassword(password)
                        )
                    },
                    label = "Password"
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                AuthenticationButtons(
                    title = "Login",
                    viewModel = viewModel,
                    uiState = loginSate
                )

                copyWrite(copyWriteYear="2022")

                AuthenticationErrorDialogs(
                    isDialogShow = loginSate.isDialogShow,
                    isDialogMessage = loginSate.isDialogMessage,
                    isDialogTitle = loginSate.isDialogTitle,
                    onDismissRequest = {
                        handleEvent(
                            AuthEvent.ShowUserDialog(
                                message = "",
                                viewStatus = false
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.padding(bottom = 15.dp))
                CircularPropagations(
                    status = loginSate.loadingProgressBar
                )

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when (event) {
                            is LoginAuthState.Success -> {
                                if(event.status==200) {
                                    onClick()
                                }
                            }
                            is LoginAuthState.Error->{
                                handleEvent(
                                    AuthEvent.ShowUserDialog(
                                        message = event.error,
                                        viewStatus = true
                                    )
                                )
                            }
                            else -> Unit
                        }
                        handleEvent(
                            AuthEvent.ChangeUserProgressBar(
                                loader = false
                            )
                        )
                    }
                }
            }
        }
    }
}
