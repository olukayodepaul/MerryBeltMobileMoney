package com.example.merrybeltmobilemoney.provider.api.api_provider_domain

import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.UsersInfoDomain
import com.example.merrybeltmobilemoney.ui.auth.auth_data.CustomerValidations
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import com.example.merrybeltmobilemoney.ui.home.home_data.Banks
import com.example.merrybeltmobilemoney.ui.home.home_data.NetworkMgtReq
import com.example.merrybeltmobilemoney.ui.home.home_data.NetworkMgtRes
import com.example.merrybeltmobilemoney.ui.home.home_data.TestData
import retrofit2.Response

interface MerryBeltApiRepository {

    suspend fun login(
        terminalId: String,
        sessionId: String,
        data: CustomerValidations,
    ): Response<LoginCredential>


//    suspend fun login(
//        requestTime: String,
//        apiHashKey: String,
//        apiUserId: Int,
//        data: LoginCredential,
//    ): Response<LoginResponse>

    suspend fun token(): String

    suspend fun apiUser(): String

    suspend fun apiID(): Int

    suspend fun saveTerminalId(terminalId: String)

    suspend fun saveAccountNumber(accountNumber: String)

    suspend fun saveBalance(balance: String)

    suspend fun saveAccountName(accountName: String)

    suspend fun saveSessionId(sessionId: String)

    fun loadUserInfo(): UsersInfoDomain

    suspend fun isNetworkApi(data: NetworkMgtReq): Response<NetworkMgtRes>

    suspend fun getBanks(terminalId: String,sessionId: String, data: TestData): Response<Banks>

}