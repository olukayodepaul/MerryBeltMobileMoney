package com.example.merrybeltmobilemoney.ui.auth.auth_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LoginCredential(
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("loginPasswordMD5")
    @Expose
    val loginPasswordMD5: String
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
    @SerializedName("balance")
    @Expose
    val balance: Balance? = null,
)

data class Balance(
    @SerializedName("balance")
    @Expose
    val balance: String? = "",
    @SerializedName("loanAmount")
    @Expose
    val loanAmount: Float? = 0f,
    @SerializedName("cashierLoanAmount")
    @Expose
    val cashierLoanAmount: Float? = 0f,
    @SerializedName("instantBetSettlementBalance")
    @Expose
    val instantBetSettlementBalance: Float? = 0f,
)

