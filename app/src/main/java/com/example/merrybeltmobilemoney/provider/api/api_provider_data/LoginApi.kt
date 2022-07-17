package com.example.merrybeltmobilemoney.provider.api.api_provider_data

import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {

    @POST("/merry/auth")
    suspend fun login(
        @Header("Request-Time")  requestTime: String,
        @Header("Api-Hash-Key")  apiHashKey: String,
        @Header("Api-User-Id")  apiUserId: Int,
        @Body data: LoginCredential
    ): Response<LoginResponse>


}