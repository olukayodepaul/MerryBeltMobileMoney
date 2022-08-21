package com.example.merrybeltmobilemoney.provider.api.api_provider_data


import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.FundTrans
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.ValidateAccNumber
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.ValidateAccNumberResponse
import retrofit2.Response
import retrofit2.http.*

interface MerryBeltApi {

    @POST("/resd/account-validation")
    suspend fun validateAccNumber(
        @Header("terminalId")  terminalId: String,
        @Header("sessionId")  sessionId: String,
        @Body data: ValidateAccNumber
    ): Response<ValidateAccNumberResponse>

    @POST("/resd/transaction")
    suspend fun fundTransfer(
        @Header("terminalId")  terminalId: String,
        @Header("sessionId")  sessionId: String,
        @Body data: FundTrans
    ): Response<ValidateAccNumberResponse>

}