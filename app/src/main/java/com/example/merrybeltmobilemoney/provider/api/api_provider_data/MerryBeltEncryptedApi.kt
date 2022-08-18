package com.example.merrybeltmobilemoney.provider.api.api_provider_data


import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.EncryptedBankList
import retrofit2.Response
import retrofit2.http.*

interface MerryBeltEncryptedApi {

    @GET("/resd/ussd-banks")
    suspend fun getEncryptedBankList(
        @Header("terminalId")  terminalId: String,
        @Header("sessionId")  sessionId: String,
    ): Response<EncryptedBankList>

}