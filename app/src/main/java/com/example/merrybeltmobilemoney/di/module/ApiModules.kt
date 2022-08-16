package com.example.merrybeltmobilemoney.di.module


import com.example.merrybeltmobilemoney.BuildConfig
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.LoginApi
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.MerryBeltApi
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.MerryBeltEncryptedApi
import com.example.merrybeltmobilemoney.util.BasicTransAuthInterceptor
import com.example.merrybeltmobilemoney.util.Constant.TRANSACTION_URL
import com.example.merrybeltmobilemoney.util.TransportInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModules {

    @Singleton
    @Provides
    fun provideLoginApi(): LoginApi {

        val supportInterceptor = TransportInterceptor(
            username = "restdevice",
            password = "5NDM1NjckJV4KK",
        )

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(supportInterceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }

        return Retrofit.Builder()
            .baseUrl("https://a6a8-105-112-30-112.eu.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClientBuilder.build())
            .build()
            .create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTranApi(): MerryBeltApi {

        val supportInterceptor = BasicTransAuthInterceptor(
            username = "restdevice",
            password = "5NDM1NjckJV4KK"
        )

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(supportInterceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }

        return Retrofit.Builder()
            .baseUrl(TRANSACTION_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClientBuilder.build())
            .build()
            .create(MerryBeltApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEncryptedApi(): MerryBeltEncryptedApi {

        val supportInterceptor = BasicTransAuthInterceptor(
            username = "restdevice",
            password = "5NDM1NjckJV4KK"
        )

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(supportInterceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }

        return Retrofit.Builder()
            .baseUrl("https://a6a8-105-112-30-112.eu.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClientBuilder.build())
            .build()
            .create(MerryBeltEncryptedApi::class.java)
    }


}


