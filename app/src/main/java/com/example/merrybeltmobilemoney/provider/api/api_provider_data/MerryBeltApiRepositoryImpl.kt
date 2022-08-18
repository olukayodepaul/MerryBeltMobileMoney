package com.example.merrybeltmobilemoney.provider.api.api_provider_data

import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.CustomersProfile
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDao
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
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
        data: LoginCredential,
    ): Response<LoginResponse> {
        return loginApi.login(requestTime, apiHashKey, apiUserId, data)
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

    override suspend fun sessionId(sessionId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun terminalId(terminalId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun merchantId(terminalId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun businessName(businessName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun merchantName(merchantName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun bank(terminalId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun balances(balance: String) {
        TODO("Not yet implemented")
    }

    override suspend fun accountName(accountName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun accountNumber(accountNumber: String) {
        TODO("Not yet implemented")
    }

    override suspend fun category(category: String) {
        TODO("Not yet implemented")
    }

    override fun loadUserInfo(): CustomersProfile {
        return sharedPref.customerProfile()
    }


}