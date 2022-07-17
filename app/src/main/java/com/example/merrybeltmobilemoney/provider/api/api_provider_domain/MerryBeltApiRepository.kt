package com.example.merrybeltmobilemoney.provider.api.api_provider_domain

import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.UsersInfoDomain
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import retrofit2.Response

interface MerryBeltApiRepository {

    suspend fun login(
        requestTime: String,
        apiHashKey: String,
        apiUserId: Int,
        data: LoginCredential,
    ): Response<LoginResponse>

    suspend fun token(): String

    suspend fun apiUser(): String

    suspend fun apiID(): Int

    suspend fun saveShopName(shopname: String)

    suspend fun saveShopAddress(shopaddress: String)

    suspend fun saveCustomerId(customerId: String)

    suspend fun saveBalance(balance: String)

    suspend fun loadUserInfo(): UsersInfoDomain

//    suspend fun getBankList(terminalId: String, sessionId: String): Response<Banks>
//
//    suspend fun networkMgt(data: NetworkMgtReq) : Response<NetworkMgtRes>

}