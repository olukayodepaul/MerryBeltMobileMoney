package com.example.merrybeltmobilemoney.ui.home.home_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//Network Mgt(7) get session_id Response
data class NetworkMgtRes(
    @SerializedName("status")
    @Expose
    val status: Boolean? = null,
    @SerializedName("data")
    @Expose
    val data: NetworkMgtResBodyData? = null
){
    data class NetworkMgtResBodyData(
        @SerializedName("sessionId")
        @Expose
        val sessionId: String? = "",
        @SerializedName("terminalId")
        @Expose
        val terminalId: String = "",
        @SerializedName("accountNumber")
        @Expose
        val accountNumber: String = "",
        @SerializedName("balance")
        @Expose
        val balance: String = "",
        @SerializedName("accountName")
        @Expose
        val accountName: String = "",
    )
}

//BankList(1)
data class Banks(
    @SerializedName("status")
    @Expose
    val status: String? = "",
    @SerializedName("data")
    @Expose
    val data: List<AllBanks> =  emptyList()
){
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
}

//CustValid(2): Request as body data
data class CustomerValidation(
    @SerializedName("bankCode")
    @Expose
    val bankCode: String? = "",
    @SerializedName("accountNumber")
    @Expose
    val accountNumber: String = ""
)

//CustValid(3): Response data
data class CustValidStatus(
    @SerializedName("status")
    @Expose
    val status: Boolean? = null,
    @SerializedName("data")
    @Expose
    val data: CustomerValidBodyData? = null
){
    data class CustomerValidBodyData(
        @SerializedName("bankCode")
        @Expose
        val bankCode: String? = "",
        @SerializedName("accountName")
        @Expose
        val accountName: String = "",
        @SerializedName("accountNumber")
        @Expose
        val accountNumber: String = "",
        @SerializedName("walletBalance")
        @Expose
        val walletBalance: String = "",
        @SerializedName("amountCharged")
        @Expose
        val amountCharged: String = ""
    )
}

//Trans(4) : Request as body Data user ->CustValid(3) data
data class TransferFundReq(
    @SerializedName("amount")
    @Expose
    val amount: Double? = 0.0,
    @SerializedName("stan")
    @Expose
    val stan: String = "",
    @SerializedName("pin")
    @Expose
    val pin: String = "",
    @SerializedName("accountNumber")
    @Expose
    val accountNumber: String = "",
    @SerializedName("bankCode") //from CustValid(3) bank code
    @Expose
    val bankCode: String = "",
    @SerializedName("type") //SAVINGS DEFAULT CURRENT CREDIT
    @Expose
    val type: String = "",
)

//Trans(5): Response
data class TransferFundRes(
    @SerializedName("status")
    @Expose
    val status: Boolean? = null,
    @SerializedName("data")
    @Expose
    val data: TransferFundResBodyData? = null
){
    data class TransferFundResBodyData(
        @SerializedName("responseCode")
        @Expose
        val responseCode: String? = "",
        @SerializedName("description")
        @Expose
        val description: String = "",
    )
}

//Network Mgt(6) get session_id Request
data class NetworkMgtReq(
    val serialNumber: String? = null,
    val stan: String? = null,
    val onlyAccountInfo: Boolean? = null
)

//AitTime Credit (8)
data class AirtimeReq(
    val amount: Double? = 0.0,
    val pin: String? = null,
    val stan: String? = null,
    val rrn: String? = null,
    val phoneNumber: String? = null,
    val airtimeCategory: String? = null, //AIRTEL, MTN, GLO, ETISALAT
    val type: String? = null, //"AIRTIME"
)

data class AirtimeBodyData(
    @SerializedName("description")
    @Expose
    val description: String? = "",
    @SerializedName("responseCode")
    @Expose
    val responseCode: String = "",
)


//Cable tV(9):
data class CableTvReq(
    val amount: Double? = 0.0,
    val pin: String? = null,
    val stan: String? = null,
    val rrn: String? = null,
    val phoneNumber: String? = null,
    val productId: String? = null,
    val cableTVNumber: String? = null, //DSTV GOTV STARTIMES
    val type: String? = null, //"CABLE_TV"
)

data class CableTvBodyData(
    @SerializedName("description")
    @Expose
    val description: String? = "",
    @SerializedName("responseCode")
    @Expose
    val responseCode: String = "",
)

//Power(10):
data class PhcnReq(
    val amount: Double? = 0.0,
    val pin: String? = null,
    val stan: String? = null,
    val rrn: String? = null,
    val phoneNumber: String? = null,
    val meterNumber: String? = null,
    val phcnCategory: String? = null,
    val phcnPurchaseCategory: String? = null, //AEDC IKEJA
    val type: String? = null, //"PHCN"
)

data class PhcnBodyData(
    @SerializedName("description")
    @Expose
    val description: String? = "",
    @SerializedName("responseCode")
    @Expose
    val responseCode: String = "",
)

//Purchase Data(11):
data class DataPurchase(
    val amount: Double? = 0.0,
    val pin: String? = null,
    val stan: String? = null,
    val rrn: String? = null,
    val phoneNumber: String? = null,
    val meterNumber: String? = null,
    val phcnCategory: String? = null,
    val phcnPurchaseCategory: String? = null, //AEDC IKEJA
    val type: String? = null, //"PHCN"
)


//Purchase Data(11):
data class TestData(
    val amount: Double? = 0.0

)

