package com.example.merrybeltmobilemoney.provider.api.api_provider_data

import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.CustomersProfile
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDao
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.*
import retrofit2.Response
import retrofit2.http.Path


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
        sharedPref.sessionId(sessionId)
    }

    override suspend fun terminalId(terminalId: String) {
        sharedPref.terminalId(terminalId)
    }

    override suspend fun merchantId(merchantId: String) {
        sharedPref.merchantId(merchantId)
    }

    override suspend fun businessName(businessName: String) {
        sharedPref.businessName(businessName)
    }

    override suspend fun merchantName(merchantName: String) {
        sharedPref.merchantName(merchantName)
    }

    override suspend fun bank(bank: String) {
        sharedPref.bank(bank)
    }

    override suspend fun balances(balance: String) {
        sharedPref.balances(balance)
    }

    override suspend fun accountName(accountName: String) {
        sharedPref.accountName(accountName)
    }

    override suspend fun accountNumber(accountNumber: String) {
        sharedPref.accountNumber(accountNumber)
    }

    override suspend fun category(category: String) {
        sharedPref.category(category)
    }

    override suspend fun stan(stan: String) {
        sharedPref.stan(stan)
    }

    override fun customerProfile(): CustomersProfile {
        return sharedPref.customerProfile()
    }

    override suspend fun getEncryptedBankList(
        terminalId: String,
        sessionId: String
    ): Response<EncryptedBankList> {
        return merryBeltEncryptedApi.getEncryptedBankList(terminalId, sessionId)
    }

    override suspend fun validateAccNumber(
        terminalId: String,
        sessionId: String,
        data: ValidateAccNumber
    ): Response<ValidateAccNumberResponse> {
        return merryBeltApi.validateAccNumber(terminalId, sessionId, data)
    }

    override suspend fun fundTransfer(
        terminalId: String,
        sessionId: String,
        data: FundTrans
    ): Response<ValidateAccNumberResponse> {
        return merryBeltApi.fundTransfer(terminalId, sessionId, data)
    }

    override suspend fun getBillingProduct(
        terminalId: String,
        sessionId: String,
        category: String
    ): Response<BillingProducts> {
        return merryBeltEncryptedApi.getBillingProduct(terminalId, sessionId, category)
    }

}