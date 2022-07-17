package com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain

import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.UsersInfoDomain


interface MerryBeltPrefRepository {

    suspend fun saveShopName(shopname: String)
    suspend fun saveShopAddress(shopaddress: String)
    suspend fun saveCustomerId(customerId: String)
    suspend fun saveBalance(balance: String)
    suspend fun loadUserInfo(): UsersInfoDomain

    //suspend fun loadUserInfo(): UsersInfoDomain

    companion object {
        const val KEY_SHOP = "shopname"
        const val KEY_ADDRESS = "shopaddress"
        const val KEY_CUSTOMERID = "customerId"
        const val KEY_BALANCE = "balance"
    }
}


