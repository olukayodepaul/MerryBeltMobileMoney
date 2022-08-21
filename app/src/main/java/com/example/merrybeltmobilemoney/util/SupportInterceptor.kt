package com.example.merrybeltmobilemoney.util


import android.util.Base64
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.Buffer


class BasicTransAuthInterceptor(username: String, password: String) : Interceptor {

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


class TransportInterceptor(username: String, password: String) : Interceptor {

    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        val buffer = Buffer()
        original.body?.writeTo(buffer)
        val strOldBody: String = buffer.readUtf8()
        buffer.clear()
        buffer.close()

        val encryptedRequest = Base64.encodeToString(EncryptionUtil().isEncryption(strOldBody, original.headers["sessionId"]!!), Base64.NO_WRAP).trim()

        val request = original
            .newBuilder()
            .addHeader("Authorization", credentials)
            .addHeader("terminalId", original.headers["terminalId"]!!)
            .addHeader("sessionId", original.headers["sessionId"]!!)
            .addHeader("Content-Type", "text/plain")
            .method(original.method, encryptedRequest.toRequestBody("text/plain; ".toMediaTypeOrNull()))
            .build()
        return chain.proceed(request)

    }
}
