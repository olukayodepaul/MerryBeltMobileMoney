package com.example.merrybeltmobilemoney.util

import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


object Constant {
    const val LOGIN_BASE_URL :String = "https://test.merrybet.com"
    const val TRANSACTION_URL: String = "https://service-dev.trylux.africa"
    val gson = GsonBuilder().create()
}