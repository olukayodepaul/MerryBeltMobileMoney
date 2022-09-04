package com.example.merrybeltmobilemoney.ui.auth.auth_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginCredential(
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("loginPasswordMD5")
    @Expose
    val loginPasswordMD5: ByteArray
)

data class LoginResponse(
    @SerializedName("errorStatusCode")
    @Expose
    val errorStatusCode: Int? = 0,
    @SerializedName("customerId")
    @Expose
    val customerId: String? = "",
    @SerializedName("shopName")
    @Expose
    val shopName: String? = "",
    @SerializedName("shopAddress")
    @Expose
    val shopAddress: String? = "",
    @SerializedName("errorMessage")
    @Expose
    val errorMessage: String? = null,
//    @SerializedName("balance")
//    @Expose
//    val balance: Balance? = null,
)

//data class Balance(
//    @SerializedName("balance")
//    @Expose
//    val balance: String? = "",
//    @SerializedName("loanAmount")
//    @Expose
//    val loanAmount: Float? = 0f,
//    @SerializedName("cashierLoanAmount")
//    @Expose
//    val cashierLoanAmount: Float? = 0f,
//    @SerializedName("instantBetSettlementBalance")
//    @Expose
//    val instantBetSettlementBalance: Float? = 0f,
//)

data class NetworkMgt(
    val serialNumber: String,
    val stan: String,
    val onlyAccountInfo: Boolean
)


data class NetworkMgtResponse(
    @SerializedName("status")
    @Expose
    val status: Boolean? = null,
    @SerializedName("data")
    @Expose
    val data: NetworkMgtData? = null,
)

data class NetworkMgtData(
    @SerializedName("sessionId")
    @Expose
    val sessionId: String? = null,
    @SerializedName("terminalId")
    @Expose
    val terminalId: String? = null,
    @SerializedName("merchantId")
    @Expose
    val merchantId: String? = null,
    @SerializedName("balance")
    @Expose
    val balance: String? = null,
    @SerializedName("accountName")
    @Expose
    val accountName: String? = null,
    @SerializedName("accountNumber")
    @Expose
    val accountNumber: String? = null,
    @SerializedName("bank")
    @Expose
    val bank: String? = null
)






