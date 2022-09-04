package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EncryptedBankList(
    @SerializedName("status")
    @Expose
    val status: String? = "",
    @SerializedName("data")
    @Expose
    val data: String =  ""
)

data class AllBanks(
    @SerializedName("url")
    @Expose
    val url: String? = "",
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("code")
    @Expose
    val code: String = ""
)

data class ValidateAccNumber(
    val bankCode: String? = "",
    val accountNumber: String =  ""
)

data class ValidateAccNumberResponse(
    @SerializedName("status")
    @Expose
    val status: Boolean? = null,
    @SerializedName("data")
    @Expose
    val data: String =  "",
    @SerializedName("message")
    @Expose
    val message: String =  "",
)

data class ValidationData(
    @SerializedName("bankCode")
    @Expose
    val bankCode: String? = "",
    @SerializedName("accountName")
    @Expose
    val accountName: String? = "",
    @SerializedName("accountNumber")
    @Expose
    val accountNumber: String? = "",
    @SerializedName("walletBalance")
    @Expose
    val walletBalance: String? = "",
    @SerializedName("amountCharged")
    @Expose
    val amountCharged: String? = "",
)

data class FundTrans(
    val amount: String? = "",
    val stan: String =  "",
    val pin: String =  "",
    val accountNumber: String =  "",
    val bankCode: String =  "",
    val type: String =  "",
)

data class DecryptedTransData(
    @SerializedName("description")
    @Expose
    val description: String? = "",
    @SerializedName("responseCode")
    @Expose
    val responseCode: String? = "",
)

data class BillingProducts(
    @SerializedName("status")
    @Expose
    val status: String? = "",
    @SerializedName("data")
    @Expose
    val data: String =  ""
)


