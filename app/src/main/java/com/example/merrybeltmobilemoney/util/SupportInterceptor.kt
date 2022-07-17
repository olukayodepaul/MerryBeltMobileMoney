package com.example.merrybeltmobilemoney.util

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicLoginAuthInterceptor(username: String, password: String): Interceptor {

    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request
            .newBuilder()
            .header("Authorization", credentials)
            .build()
        return chain.proceed(request)
    }
}

class BasicTransAuthInterceptor(username: String, password: String): Interceptor {

    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request
            .newBuilder()
            .header("Authorization", credentials)
            .build()
        return chain.proceed(request)
    }
}