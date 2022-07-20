package com.example.merrybeltmobilemoney.util


import android.util.Log
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.util.EncryptionUtil.decrypt
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okio.Buffer
import javax.inject.Inject
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject


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


class TransportInterceptor(username: String, password: String) : Interceptor {

    @Inject
    lateinit var repo: MerryBeltApiRepository

    var contentType = "application/text; charset=utf-8".toMediaTypeOrNull()

    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        //val body = original.body //Encryp Data
//
        val buffer = Buffer()
        original.body?.writeTo(buffer)
        val strOldBody: String = buffer.readUtf8()
        buffer.clear()
        buffer.close()

        //Log.d("Epokhai", "${original.body?.contentType()}")

        val strNewBody = (strOldBody)

        val strNewBod = strNewBody.toRequestBody("application/json".toMediaType())

        val request = original
            .newBuilder()
            .header("Authorization", credentials)
            .method(original.method, strNewBod)
            .build()

        val req = chain.proceed(request)
        val encData = req.body?.string().toString() //decrypt here

        return req.newBuilder()
            .body(encData.toResponseBody(req.body?.contentType()))
            .build()

    }


}