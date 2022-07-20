package com.example.merrybeltmobilemoney.provider.preference.pref_provider_data

import android.content.SharedPreferences
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository

class MerryBeltPrefRepositoryImpl(
    private val sharedPref: SharedPreferences
): MerryBeltPrefRepository {

    override suspend fun saveTerminalId(terminalId: String) {
        sharedPref.edit()
            .putString(MerryBeltPrefRepository.KEY_TERMINAL, terminalId).apply()
    }

    override suspend fun saveAccountNumber(accountNumber: String) {
        sharedPref.edit()
            .putString(MerryBeltPrefRepository.KEY_ACC_NUMBER, accountNumber).apply()
    }

    override suspend fun saveBalance(balance: String) {
        sharedPref.edit()
            .putString(MerryBeltPrefRepository.KEY_ACC_BALANCE, balance).apply()
    }

    override suspend fun saveAccountName(accountName: String) {
        sharedPref.edit()
            .putString(MerryBeltPrefRepository.KEY_ACC_NAME, accountName).apply()
    }

    override suspend fun saveSessionId(sessionId: String) {
        sharedPref.edit()
            .putString(MerryBeltPrefRepository.KEY_SESSION_ID, sessionId)
    }


    override  fun loadUserInfo(): UsersInfoDomain {

        val terminalId = sharedPref.getString(MerryBeltPrefRepository.KEY_TERMINAL, "")
        val accountNumber = sharedPref.getString(MerryBeltPrefRepository.KEY_ACC_NUMBER, "")
        val balance = sharedPref.getString(MerryBeltPrefRepository.KEY_ACC_BALANCE, "")
        val accountName = sharedPref.getString(MerryBeltPrefRepository.KEY_ACC_NAME, "")
        val sessionId = sharedPref.getString(MerryBeltPrefRepository.KEY_SESSION_ID, "")

        return UsersInfoDomain(
            terminalId = terminalId!!,
            accountNumber = accountNumber!!,
            balance = balance,
            accountName = accountName!!,
            sessionId = sessionId!!
        )

    }
}
