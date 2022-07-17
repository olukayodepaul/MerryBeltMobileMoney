package com.example.merrybeltmobilemoney.provider.api.api_provider_data


import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface MerryBeltApi {

//
//    @GET("/resd/banks")
//    suspend fun getBanks(
//        @Header("terminalId")  terminalId: String,
//        @Header("sessionId")  sessionId: String,
//    ): Response<Banks> //bank List
//
//    @POST("/resd/account-validation")
//    suspend fun customerValidation(
//        @Header("terminalId")  terminalId: String,
//        @Header("sessionId")  sessionId: String,
//        @Body data: CustomerValidation //cust validation
//    )
//
//    @POST("/resd/transaction")
//    suspend fun transferFun(
//        @Header("terminalId")  terminalId: String,
//        @Header("sessionId")  sessionId: String,
//        @Body data: TransferFundReq
//    ): Response<TransferFundRes> //Transfer fun
//
//    @POST("/resd/network-mgt")
//    suspend fun networkMgt(
//        @Body data: NetworkMgtReq
//    ): Response<NetworkMgtRes> //Network
//
//    @POST("/resd/transaction")
//    suspend fun airTime(
//        @Header("terminalId")  terminalId: String,
//        @Header("sessionId")  sessionId: String,
//        @Body data: AirtimeReq
//    ): Response<AirtimeBodyData> //Airtime
//
//    @POST("/resd/transaction")
//    suspend fun cableTv(
//        @Header("terminalId")  terminalId: String,
//        @Header("sessionId")  sessionId: String,
//        @Body data: CableTvReq
//    ): Response<CableTvBodyData> //Cable Tv
//
//    @POST("/resd/transaction")
//    suspend fun getPhcn(
//        @Header("terminalId")  terminalId: String,
//        @Header("sessionId")  sessionId: String,
//        @Body data: PhcnReq
//    ): Response<PhcnBodyData> //Phcn
//

}