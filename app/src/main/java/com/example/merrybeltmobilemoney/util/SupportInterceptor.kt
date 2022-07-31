package com.example.merrybeltmobilemoney.util


import android.util.Base64
import android.util.Log
import com.example.merrybeltmobilemoney.ui.auth.auth_data.LoginCredential
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


class BasicLoginAuthInterceptor(username: String, password: String) : Interceptor {

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


class TransportInterceptor(username: String, password: String, moshi: Moshi) : Interceptor {

    private var credentials: String = Credentials.basic(username, password)
    private val moshi = moshi

    override fun intercept(chain: Interceptor.Chain): Response {


        val original = chain.request()

        val buffer = Buffer()
        original.body?.writeTo(buffer)
        val strOldBody: String = buffer.readUtf8()
        buffer.clear()
        buffer.close()

        Log.d("Pohai 1", "$strOldBody")
        Log.d("Pohai 5", "${original.headers}")
        Log.d("Pohai 6", "${original.method}")
        Log.d("Pohai 7", "${credentials}")

        val encryptedRequest = Base64.encodeToString(EncryptionUtil().isEncryption(strOldBody), Base64.NO_WRAP).trim()

        Log.d("Pohai 2", encryptedRequest)
        val decryptedData = EncryptionUtil().isDecryption(encryptedRequest)
        Log.d("Pohai 4", "$decryptedData")

        val request = original
            .newBuilder()
            .addHeader("Authorization", credentials)
            .addHeader("terminalId", original.headers["terminalId"]!!)
            .addHeader("sessionId", original.headers["sessionId"]!!)
            .addHeader("Content-Type", "text/plain")
            .method(original.method, encryptedRequest.toRequestBody("text/plain".toMediaTypeOrNull()))
            .build()

        val req = chain.proceed(request)

        Log.d("Pohai 3", "$req")

        val encData = req.body?.string().toString()


//        Log.d("EPOKHAI 55","${EncryptionUtil().epokhaiDecrypt("HHsdDokaQaAnxL1nx/0SldLrOdxf+faEV0Be0Ian8qvxD1OEYt/snFDIQqjpAFUE")}")

        return req.newBuilder()
            .body(encData.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()

    }
}