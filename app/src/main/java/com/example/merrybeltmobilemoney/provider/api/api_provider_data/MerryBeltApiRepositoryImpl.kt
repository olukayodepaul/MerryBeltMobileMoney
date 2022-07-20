package com.example.merrybeltmobilemoney.provider.api.api_provider_data

import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.UsersInfoDomain
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDao
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import com.example.merrybeltmobilemoney.ui.home.home_data.Banks
import com.example.merrybeltmobilemoney.ui.home.home_data.NetworkMgtReq
import com.example.merrybeltmobilemoney.ui.home.home_data.NetworkMgtRes
import com.example.merrybeltmobilemoney.ui.home.home_data.TestData
import retrofit2.Response


class MerryBeltApiRepositoryImpl(

    private val merryBeltApi: MerryBeltApi,
    private val merryBeltEncryptedApi: MerryBeltEncryptedApi,
    private val loginApi: LoginApi,
    private val authKey: String,
    private val apiUser: String,
    private val apiID: Int,
    private val merryBeltRoomDao: MerryBeltRoomDao,
    private val sharedPref: MerryBeltPrefRepository

) : MerryBeltApiRepository {

    override suspend fun login(
        requestTime: String,
        apiHashKey: String,
        apiUserId: Int,
        data: LoginCredential
    ): Response<LoginResponse> {
        return loginApi.login(
            requestTime,
            apiHashKey,
            apiUserId,
            data
        )
    }

    override suspend fun token(): String {
        return authKey
    }

    override suspend fun apiUser(): String {
        return apiUser
    }

    override suspend fun apiID(): Int {
        return apiID
    }

    override suspend fun saveTerminalId(terminalId: String) {
        sharedPref.saveTerminalId(terminalId)
    }

    override suspend fun saveAccountNumber(accountNumber: String) {
        sharedPref.saveAccountNumber(accountNumber)
    }


    override suspend fun saveBalance(balance: String) {
        sharedPref.saveBalance(balance)
    }

    override suspend fun saveAccountName(accountName: String) {
        sharedPref.saveAccountName(accountName)
    }

    override suspend fun saveSessionId(sessionId: String) {
         sharedPref.saveSessionId(sessionId)
    }

    override fun loadUserInfo(): UsersInfoDomain {
        return sharedPref.loadUserInfo()
    }

    override suspend fun isNetworkApi(data: NetworkMgtReq): Response<NetworkMgtRes> {
        return merryBeltApi.getNetwork(data)
    }

    override suspend fun getBanks(terminalId: String, sessionId: String, data: TestData): Response<Banks> {
        return merryBeltEncryptedApi.getBanks(terminalId, sessionId, data)
    }
}