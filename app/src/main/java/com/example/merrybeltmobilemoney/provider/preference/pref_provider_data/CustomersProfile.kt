package com.example.merrybeltmobilemoney.provider.preference.pref_provider_data

data class CustomersProfile (
    val sessionId:String = "",
    val terminalId: String = "",
    val merchantId: String? = "",
    val businessName: String = "",
    val merchantName: String ="",
    val bank: String ="",
    val balance: String ="",
    val accountName: String ="",
    val accountNumber: String ="",
    val category: String =""
)
