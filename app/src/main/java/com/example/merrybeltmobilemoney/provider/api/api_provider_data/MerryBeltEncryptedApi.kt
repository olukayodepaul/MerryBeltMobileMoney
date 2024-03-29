package com.example.merrybeltmobilemoney.provider.api.api_provider_data


import com.example.merrybeltmobilemoney.ui.auth.auth_data.NetworkMgt
import com.example.merrybeltmobilemoney.ui.auth.auth_data.NetworkMgtResponse
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.BillingProducts
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.EncryptedBankList
import retrofit2.Response
import retrofit2.http.*

interface MerryBeltEncryptedApi {

    @GET("/resd/ussd-banks")
    suspend fun getEncryptedBankList(
        @Header("terminalId")  terminalId: String,
        @Header("sessionId")  sessionId: String,
    ): Response<EncryptedBankList>

    @GET("/resd/bills/{category}")
    suspend fun getBillingProduct(
        @Header("terminalId")  terminalId: String,
        @Header("sessionId")  sessionId: String,
        @Path("category") category: String
    ): Response<BillingProducts>

    @POST("/resd/network-mgt")
    suspend fun mgt(
        @Body data: NetworkMgt
    ): Response<NetworkMgtResponse>

}