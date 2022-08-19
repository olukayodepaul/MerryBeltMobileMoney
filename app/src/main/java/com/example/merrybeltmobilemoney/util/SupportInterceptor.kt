package com.example.merrybeltmobilemoney.util


import android.util.Base64
import android.util.Log
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
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

        Log.d("CHECKEPO BODY","${strOldBody}")

        val encryptedRequest = Base64.encodeToString(EncryptionUtil().isEncryption(strOldBody, original.headers["sessionId"]!!), Base64.NO_WRAP).trim()
        Log.d("CHECKEPO ENCRYPTED","${encryptedRequest}")
        Log.d("CHECKEPO SESSION","${original.headers["sessionId"]!!}")

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


//        val req = chain.proceed(request)
//
//        val encData = req.body?.string().toString()
//        val decryptedData = EncryptionUtil().isDecryption(encData)
//        val gson = Gson()
//        return req.newBuilder()
//            .body(decryptedData.toResponseBody("application/json".toMediaTypeOrNull()))
//            .build()