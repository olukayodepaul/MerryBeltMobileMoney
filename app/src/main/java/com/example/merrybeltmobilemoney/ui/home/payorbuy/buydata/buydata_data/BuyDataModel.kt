package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataProductList(
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
    val id: Int? = 0,
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
