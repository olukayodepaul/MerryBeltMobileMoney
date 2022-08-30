package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhcnProductList(
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
)

data class MeterType(
    val type: String = ""
)