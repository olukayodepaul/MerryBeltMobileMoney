package com.example.merrybeltmobilemoney.util

import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject


class EncryptionUtil {

    @Inject
    lateinit var repo: MerryBeltApiRepository

    private val algo = "DESede/CBC/PKCS5Padding"
    private val des = "DESede"
    private val md5 = "md5"
    private val standard = StandardCharsets.UTF_8

    fun isEncryption(message: String): ByteArray {

        val md = MessageDigest.getInstance(md5)
        val digestOfPassword = md.digest(repo.customerProfile().sessionId.toByteArray(standard))
        val keyBytes = Arrays.copyOf(digestOfPassword, 24)
        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }
        val key: SecretKey = SecretKeySpec(keyBytes, des)
        val iv = IvParameterSpec(ByteArray(8))
        val cipher = Cipher.getInstance(algo)
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val plainTextBytes = message.toByteArray(StandardCharsets.UTF_8)
        return cipher.doFinal(plainTextBytes)
    }

    fun isDecryption(encryptedText: String): String {
            val md = MessageDigest.getInstance(md5)
            val digestOfPassword = md.digest(repo.customerProfile().sessionId.toByteArray(standard))
            val keyBytes = Arrays.copyOf(digestOfPassword, 24)
            var j = 0
            var k = 16
            while (j < 8) {
                keyBytes[k++] = keyBytes[j++]
            }
            val key: SecretKey = SecretKeySpec(keyBytes, des)
            val iv = IvParameterSpec(ByteArray(8))
            val decipher = Cipher.getInstance(algo)
            decipher.init(Cipher.DECRYPT_MODE, key, iv)
            val decodedBuffer = org.bouncycastle.util.encoders.Base64.decode(encryptedText)
            val plainText = decipher.doFinal(decodedBuffer)
            return String(plainText)
    }

    fun passwordM5D(input:String) :  ByteArray{
        val md = MessageDigest.getInstance(md5)
        return md.digest(input.toByteArray(standard))
    }


}
