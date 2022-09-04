package com.example.merrybeltmobilemoney.ui.auth.auth_presenter


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.auth.auth_data.*
import com.example.merrybeltmobilemoney.util.getHash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: MerryBeltApiRepository, private val appContext: Application) : ViewModel(){

    private val _apiEvent = Channel<LoginAuthState>()
    val uiEvent = _apiEvent.receiveAsFlow()

    var uiState = MutableStateFlow(AuthState())

    private fun updateUsernameEvent(username: String) {
        uiState.value = uiState.value.copy(
            username = username
        )
    }

    private fun showUserDialogEvent(isDialogMessage: String, isDialogShow: Boolean) {
        uiState.value = uiState.value.copy(
            isDialogMessage = isDialogMessage,
            isDialogShow = isDialogShow,
        )
    }

    private fun updatePasswordEvent(password: String) {
        uiState.value = uiState.value.copy(
            password = password
        )
    }

    private fun updateProgressBarEvent(loader: Boolean) {
        uiState.value = uiState.value.copy(
            loadingProgressBar = loader
        )
    }

    fun authApiRequest(userName: String, password: ByteArray) = viewModelScope.launch {

        repo

            if (userName.isEmpty() || password.isEmpty()) {

                showUserDialogEvent(
                    isDialogMessage = "Please enter correct username and password!",
                    isDialogShow = true,
                )

            } else {

                updateProgressBarEvent(loader = true)

                try {

                    val requestTime = SimpleDateFormat("yyyyMMddHHmmssZ").format(Date())
                    val apiHashKey = getHash("${repo.apiUser()}${repo.token()}$requestTime")
                    val apiUser = repo.apiID()

                    val bodyRequest = LoginCredential(userName, password)
                    val handleApiRequest = repo.login(requestTime, apiHashKey, apiUser, bodyRequest)
                    val bodyPayLoad = handleApiRequest.body()

                    if(bodyPayLoad!!.errorStatusCode == 1 && handleApiRequest.code() == 200 && handleApiRequest.isSuccessful) {

                        val makeMgtRequest = NetworkMgt(repo.userSerialNumber(), repo.userStan(), repo.userOnlyAccountInfo())
                        val mgtResponseReceiver = repo.mgt(makeMgtRequest).body()

                        repo.balances(balance = mgtResponseReceiver!!.data!!.balance!!)
                        repo.accountNumber(accountNumber = mgtResponseReceiver!!.data!!.accountNumber!!)
                        repo.sessionId(sessionId = mgtResponseReceiver!!.data!!.sessionId!!)
                        repo.terminalId(terminalId = mgtResponseReceiver!!.data!!.terminalId!!)
                        repo.stan(stan = repo.userStan())
                        _apiEvent.send(LoginAuthState.Success(status = 200))

                    }else{
                        _apiEvent.send(LoginAuthState.Error(error =  bodyPayLoad.errorMessage!!))
                    }

                }catch (e:Throwable) {
                    _apiEvent.send(LoginAuthState.Error(error =  e.message.toString()))
                }
            }
        }

    fun authEventHandler(authenticationEvent: AuthEvent) {
        when (authenticationEvent) {
            is AuthEvent.ChangeUserName -> {
                updateUsernameEvent(authenticationEvent.username)
            }
            is AuthEvent.ChangeUserPassword -> {
                updatePasswordEvent(authenticationEvent.passwords)
            }
            is AuthEvent.ChangeUserProgressBar -> {
                updateProgressBarEvent(authenticationEvent.loader)
            }
            is AuthEvent.ShowUserDialog -> {
                showUserDialogEvent(
                    authenticationEvent.message!!,
                    authenticationEvent.viewStatus!!
                )
            }
        }
    }


}