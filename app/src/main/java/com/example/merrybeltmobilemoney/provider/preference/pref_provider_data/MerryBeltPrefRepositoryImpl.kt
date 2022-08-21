package com.example.merrybeltmobilemoney.provider.preference.pref_provider_data

import android.content.SharedPreferences
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository

class MerryBeltPrefRepositoryImpl(
    private val sharedPref: SharedPreferences
): MerryBeltPrefRepository {

    override suspend fun sessionId(sessionId: String) {
        sharedPref.edit()
            .putString(MerryBeltPrefRepository.KEY_SESSION_ID, sessionId).apply()
    }

    override suspend fun terminalId(terminalId: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_TERMINAL, terminalId).apply()
    }

    override suspend fun merchantId(merchantId: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_MERCHANT_ID, merchantId).apply()
    }

    override suspend fun businessName(businessName: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_BUSINESS_NAME, businessName).apply()
    }

    override suspend fun merchantName(merchantName: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_MERCHANT_NAME, merchantName).apply()
    }

    override suspend fun bank(bank: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_BANK, bank).apply()
    }

    override suspend fun balances(balance: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_BALANCE, balance).apply()
    }

    override suspend fun accountName(accountName: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_ACC_NAME, accountName).apply()
    }

    override suspend fun accountNumber(accountNumber: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_ACC_NUMBER, accountNumber).apply()
    }

    override suspend fun category(category: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_CATEGORY, category).apply()
    }

    override suspend fun stan(stan: String) {
        sharedPref.edit().putString(MerryBeltPrefRepository.KEY_STAN, stan).apply()
    }

    override fun customerProfile(): CustomersProfile {

        val sessionId = sharedPref.getString(MerryBeltPrefRepository.KEY_SESSION_ID, "")
        val terminalId = sharedPref.getString(MerryBeltPrefRepository.KEY_TERMINAL, "")
        val merchantId = sharedPref.getString(MerryBeltPrefRepository.KEY_MERCHANT_ID, "")
        val businessName = sharedPref.getString(MerryBeltPrefRepository.KEY_BUSINESS_NAME, "")
        val merchantName = sharedPref.getString(MerryBeltPrefRepository.KEY_MERCHANT_NAME, "")
        val bank = sharedPref.getString(MerryBeltPrefRepository.KEY_BANK, "")
        val balance = sharedPref.getString(MerryBeltPrefRepository.KEY_BALANCE, "")
        val accountName = sharedPref.getString(MerryBeltPrefRepository.KEY_ACC_NAME, "")
        val accountNumber = sharedPref.getString(MerryBeltPrefRepository.KEY_ACC_NUMBER, "")
        val category = sharedPref.getString(MerryBeltPrefRepository.KEY_CATEGORY, "")
        val stan = sharedPref.getString(MerryBeltPrefRepository.KEY_STAN, "")

        return CustomersProfile(
            sessionId = sessionId!!,
            terminalId = terminalId!!,
            merchantId = merchantId,
            businessName = businessName!!,
            merchantName = merchantName!!,
            bank = bank!!,
            balance = balance!!,
            accountName = accountName!!,
            accountNumber = accountNumber!!,
            category = category!!,
            stan= stan!!
        )
    }

}
