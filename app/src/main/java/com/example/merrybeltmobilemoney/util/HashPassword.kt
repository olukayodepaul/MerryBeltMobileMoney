package com.example.merrybeltmobilemoney.util


import android.annotation.SuppressLint
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

fun getHash(input:String): String{
    val md: MessageDigest = MessageDigest.getInstance("SHA-512")
    val messageDigest = md.digest(input.toByteArray())
    val no = BigInteger(1, messageDigest)
    var hashtext: String = no.toString(16)
    while (hashtext.length < 128) {
        hashtext = "0$hashtext"
    }
    return hashtext.uppercase()
}

@SuppressLint("SimpleDateFormat")
val currentDate: String? = SimpleDateFormat("yyyyMMddHHMMssZ").format(Date())
val currentDat: String? = SimpleDateFormat("yyyy-MM-dd-HH-MM-ss-Z").format(Date())
