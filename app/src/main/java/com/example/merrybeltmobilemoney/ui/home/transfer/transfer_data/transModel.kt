package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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