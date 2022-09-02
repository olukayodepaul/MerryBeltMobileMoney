package com.example.merrybeltmobilemoney.provider.api.api_provider_domain

import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.CustomersProfile
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.*
import retrofit2.Response
import retrofit2.http.Path

interface MerryBeltApiRepository {

    suspend fun login(requestTime: String, apiHashKey: String, apiUserId: Int, data: LoginCredential, ): Response<LoginResponse>

    suspend fun token(): String

    suspend fun apiUser(): String

    suspend fun apiID(): Int

    suspend fun sessionId(sessionId: String)

    suspend fun terminalId(terminalId: String)

    suspend fun merchantId(merchantId: String)

    suspend fun businessName(businessName: String)

    suspend fun merchantName(merchantName: String)

    suspend fun bank(bank: String)

    suspend fun balances(balance: String)

    suspend fun accountName(accountName: String)

    suspend fun accountNumber(accountNumber: String)

    suspend fun category(category: String)

    suspend fun stan(stan:String)

    fun customerProfile(): CustomersProfile

    suspend fun getEncryptedBankList(terminalId: String, sessionId: String): Response<EncryptedBankList>

    suspend fun validateAccNumber(terminalId: String, sessionId: String, data: ValidateAccNumber): Response<ValidateAccNumberResponse>

    suspend fun fundTransfer(terminalId: String, sessionId: String, data: FundTrans): Response<ValidateAccNumberResponse>

    suspend fun getBillingProduct(terminalId: String, sessionId: String, category: String): Response<BillingProducts>

    suspend fun userSerialNumber(): String

    suspend fun userStan(): String

    suspend fun userOnlyAccountInfo () : Boolean


}