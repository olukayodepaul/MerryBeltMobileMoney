package com.example.merrybeltmobilemoney.provider.api.api_provider_data

import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.UsersInfoDomain
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDao
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import retrofit2.Response


class MerryBeltApiRepositoryImpl(

    private val merryBeltApi: MerryBeltApi,
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

    override suspend fun saveShopName(shopname: String) {
        sharedPref.saveShopName(shopname)
    }

    override suspend fun saveShopAddress(shopaddress: String) {
        sharedPref.saveShopAddress(shopaddress)
    }

    override suspend fun saveCustomerId(customerId: String) {
        sharedPref.saveCustomerId(customerId)
    }

    override suspend fun saveBalance(balance: String) {
        sharedPref.saveBalance(balance)
    }

    override suspend fun loadUserInfo(): UsersInfoDomain {
        return sharedPref.loadUserInfo()
    }

//    override suspend fun getBankList(terminalId: String, sessionId: String): Response<Banks> {
//        return merryBeltApi.getBanks(terminalId, sessionId)
//    }
//
//    override suspend fun networkMgt(data: NetworkMgtReq): Response<NetworkMgtRes> {
//        return merryBeltApi.networkMgt(data)
//    }

}