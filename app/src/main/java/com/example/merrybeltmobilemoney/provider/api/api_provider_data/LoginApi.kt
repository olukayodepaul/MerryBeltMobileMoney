package com.example.merrybeltmobilemoney.provider.api.api_provider_data

import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import retrofit2.Response
import retrofit2.http.*


interface LoginApi {

    @POST("/betting-shop-api/v1/authentication/admin/login")
    suspend fun login(
        @Header("Request-Time")  requestTime: String,
        @Header("Api-Hash-Key")  apiHashKey: String,
        @Header("Api-User-Id")  apiUserId: Int,
        @Body data: LoginCredential
    ): Response<LoginResponse>

}