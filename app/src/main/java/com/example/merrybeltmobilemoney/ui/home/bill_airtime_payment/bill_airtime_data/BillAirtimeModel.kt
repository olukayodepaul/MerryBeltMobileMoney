package com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaymentCategory(
    var id : Int = 0,
    var category :String = "",
)

data class NetworkList(
    var id : Int = 0,
    var category :String = "",
)

data class DataCategory(
    @SerializedName("category")
    @Expose
    val category: String? = "",
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = "",
    @SerializedName("description")
    @Expose
    val description: String? = "",
    @SerializedName("commissionFeeInPercent")
    @Expose
    val commissionFeeInPercent: String? = "",
    @SerializedName("plan")
    @Expose
    val plan: List<DataPlan>? = null,
)

data class DataPlan(
    @SerializedName("id")
    @Expose
    val id: String? = "",
    @SerializedName("price")
    @Expose
    val price: String? = "",
    @SerializedName("duration")
    @Expose
    val duration: String? = "",
    @SerializedName("package")
    @Expose
    val packages: String? = ""
)


