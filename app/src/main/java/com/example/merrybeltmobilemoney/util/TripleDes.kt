package com.example.merrybeltmobilemoney.util


import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import org.bouncycastle.util.encoders.Base64

object EncryptionUtils {



//    val jsonAdapter: JsonAdapter<TransactionRequest> = moshi.adapter(TransactionRequest::class.java)
//
//    val json: String = jsonAdapter.toJson(transactionRequest)
//
//    val encryptedRequest = Base64.encodeToString(
//        EncryptionUtil.encrypt(
//            json,
//            preferenceManager.getSessionId().first()
//        ),
//        Base64.NO_WRAP
//    ).trim()
//
//    val requestBody = encryptedRequest.toRequestBody("text/plain".toMediaTypeOrNull())


    fun encrypt(message: String, secret: String): ByteArray {

        val md = MessageDigest.getInstance("md5")
        val digestOfPassword = md.digest(secret.toByteArray(StandardCharsets.UTF_8))
        val keyBytes = Arrays.copyOf(digestOfPassword, 24)
        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }
        val key: SecretKey = SecretKeySpec(keyBytes, "DESede")
        val iv = IvParameterSpec(ByteArray(8))
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val plainTextBytes = message.toByteArray(StandardCharsets.UTF_8)
        return cipher.doFinal(plainTextBytes)
    }

    fun decrypt(message: ByteArray, secret: String): String {
        try {
            val md = MessageDigest.getInstance("md5")
            val digestOfPassword = md.digest(
                secret
                    .toByteArray(charset("utf-8"))
            )
            val keyBytes = Arrays.copyOf(digestOfPassword, 24)
            var j = 0
            var k = 16
            while (j < 8) {
                keyBytes[k++] = keyBytes[j++]
            }
            val key: SecretKey = SecretKeySpec(keyBytes, "DESede")
            val iv = IvParameterSpec(ByteArray(8))
            val decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")
            decipher.init(Cipher.DECRYPT_MODE, key, iv)
            val decodedBuffer = Base64.decode(message)
            val plainText = decipher.doFinal(decodedBuffer)
            return String(plainText)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return "error"
    }
}
