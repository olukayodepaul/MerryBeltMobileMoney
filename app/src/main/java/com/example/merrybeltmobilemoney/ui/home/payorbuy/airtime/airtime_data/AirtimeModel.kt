package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AirtimeProductList(
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
    val commissionFeeInPercent: String? = ""
)