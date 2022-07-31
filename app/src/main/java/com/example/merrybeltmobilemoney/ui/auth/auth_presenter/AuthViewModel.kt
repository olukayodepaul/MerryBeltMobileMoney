package com.example.merrybeltmobilemoney.ui.auth.auth_presenter

import android.annotation.SuppressLint
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.auth.auth_data.AuthEvent
import com.example.merrybeltmobilemoney.ui.auth.auth_data.AuthState
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginAuthState
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.home.home_data.NetworkMgtReq
import com.example.merrybeltmobilemoney.util.Constant.loginAdapter
import com.example.merrybeltmobilemoney.util.Constant.moshi
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import com.example.merrybeltmobilemoney.util.getHash
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application)
    : ViewModel(){

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

    @SuppressLint("SimpleDateFormat")
    fun AuthApiRequest(
        userName: String,
        password: String
    ) {

        viewModelScope.launch {

            val _userName = userName
            val _password = password

            if (_userName.isEmpty() || _password.isEmpty()) {
                showUserDialogEvent(
                    isDialogMessage = "Please enter correct username and password!",
                    isDialogShow = true,
                )
            } else {

                updateProgressBarEvent(loader = true)

                try {

                    val requestData = LoginCredential(
                        bankCode = "058",
                        accountNumber = "0113069289"
                    )

                    //convert Object to String
                    val toJson = loginAdapter.toJson(requestData)

                     val repo = repo.login(
                         terminalId = "2033HQOQ",
                         sessionId = "22033HQOQ-9c1ed177-3b0f-4417-8d70-76e84fb09640",
                         data = toJson
                     )

//                    val requestTime = SimpleDateFormat("yyyyMMddHHmmssZ").format(Date())
//                    val apiHashKey = getHash("${repo.apiUser()}${repo.token()}$requestTime")
//                    val apiUser = repo.apiID()
//
//                    val bodyRequest = LoginCredential(_userName, _password)
//                    val handleApiRequest = repo.login(requestTime, apiHashKey, apiUser, bodyRequest)
//
//                    val bodyPayLoad = handleApiRequest.body()
//
//                    if(bodyPayLoad!!.errorStatusCode==1){
//
//                        if(repo.loadUserInfo().balance!!.isEmpty()){
//
//                            val authData = NetworkMgtReq(
//                                serialNumber = "63201125995137",
//                                stan = "123456",
//                                onlyAccountInfo = false
//                            )
//
//                            val networkApi = repo.isNetworkApi(authData)
//
//                            if(networkApi.isSuccessful || networkApi.code()==200 || networkApi.body()!!.status==true) {
//
//                                val isNetworkResponse = networkApi.body()!!.data
//
//                                repo.saveBalance(isNetworkResponse!!.balance)
//                                repo.saveAccountName(isNetworkResponse.accountName)
//                                repo.saveAccountNumber(isNetworkResponse.accountNumber)
//                                repo.saveTerminalId(isNetworkResponse.terminalId)
//                                repo.saveSessionId(isNetworkResponse.sessionId!!)
//
//                                _apiEvent.send(LoginAuthState.Success(status = 200))
//
//                            }else{
//                                _apiEvent.send(LoginAuthState.Error(error =  "Session id error, please contact the admin"))
//                            }
//
//                        }else{
//                           _apiEvent.send(LoginAuthState.Success(status = 200))
//                        }
//
//                    }else{
//                        _apiEvent.send(LoginAuthState.Error(error =  bodyPayLoad.shopName!!,))
//                    }

                }catch (e:Throwable) {
                    Log.d("EPOKHAI 66", "${e.message}")
                    _apiEvent.send(LoginAuthState.Error(error =  e.message.toString()))
                }
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