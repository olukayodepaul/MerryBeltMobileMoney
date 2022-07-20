package com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain

import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.UsersInfoDomain


interface MerryBeltPrefRepository {

    suspend fun saveTerminalId(terminalId: String)
    suspend fun saveAccountNumber(accountNumber: String)
    suspend fun saveBalance(balance: String)
    suspend fun saveAccountName(accountName: String)
    suspend fun saveSessionId(sessionId: String)
    fun loadUserInfo(): UsersInfoDomain


    companion object {
        const val KEY_TERMINAL = "terminalId"
        const val KEY_ACC_NUMBER = "accountNumber"
        const val KEY_ACC_BALANCE = "balance"
        const val KEY_ACC_NAME= "accountName"
        const val KEY_SESSION_ID = "sessionId"
    }
}
