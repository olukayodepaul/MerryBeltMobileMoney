package com.example.merrybeltmobilemoney.provider.preference.pref_provider_data

data class UsersInfoDomain (
    val terminalId: String = "",
    val accountNumber: String = "",
    val balance: String? = "",
    val accountName: String = "",
    val sessionId: String =""
)
