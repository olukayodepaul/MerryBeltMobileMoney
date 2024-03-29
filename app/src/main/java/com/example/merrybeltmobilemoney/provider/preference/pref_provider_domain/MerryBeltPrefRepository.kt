package com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain

import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.CustomersProfile


interface MerryBeltPrefRepository {

    suspend fun sessionId(sessionId: String)
    suspend fun terminalId(terminalId: String)
    suspend fun merchantId(terminalId: String)
    suspend fun businessName(businessName: String)
    suspend fun merchantName(merchantName: String)
    suspend fun bank(bank: String)
    suspend fun balances(balance: String)
    suspend fun accountName(accountName: String)
    suspend fun accountNumber(accountNumber: String)
    suspend fun category(category: String)
    suspend fun stan(stan: String)
    fun customerProfile(): CustomersProfile

    companion object {
        const val KEY_SESSION_ID = "sessionId"
        const val KEY_TERMINAL = "terminalId"
        const val KEY_MERCHANT_ID = "merchantId"
        const val KEY_BUSINESS_NAME = "businessName"
        const val KEY_MERCHANT_NAME = "merchantName"
        const val KEY_BANK = "bank"
        const val KEY_BALANCE = "balance"
        const val KEY_ACC_NAME= "accountName"
        const val KEY_ACC_NUMBER = "accountNumber"
        const val KEY_CATEGORY = "category"
        const val KEY_STAN = "stan"
    }
}
