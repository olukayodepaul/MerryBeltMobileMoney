package com.example.merrybeltmobilemoney.util

import android.util.Base64
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject


class EncryptionUtil {

    @Inject
    lateinit var repo: MerryBeltApiRepository

    val algo = "DESede/ECB/PKCS7Padding"

    fun epokhaiEncrypt(message: String): String {
        val cipher: Cipher = Cipher.getInstance(algo)
        cipher.init(Cipher.ENCRYPT_MODE, getSecreteKey(repo.loadUserInfo().sessionId))
        val plainTextBytes = message.toByteArray(charset("UTF-8"))
        val buf: ByteArray = cipher.doFinal(plainTextBytes)
        val base64Bytes: ByteArray = Base64.encode(buf, Base64.DEFAULT)
        return String(base64Bytes)
    }

    fun epokhaiDecrypt(encryptedText: String): String? {
        val message = Base64.decode(encryptedText.toByteArray(), Base64.DEFAULT)
        val decipher = Cipher.getInstance(algo)
        decipher.init(Cipher.DECRYPT_MODE, getSecreteKey(repo.loadUserInfo().sessionId))
        val plainText = decipher.doFinal(message)
        return String(plainText, StandardCharsets.UTF_8)
    }

    private fun getSecreteKey(secretKey: String): SecretKey? {
        val md = MessageDigest.getInstance("md5")
        val digestOfPassword = md.digest(secretKey.toByteArray(charset("utf-8")))
        val keyBytes: ByteArray = Arrays.copyOf(digestOfPassword, 24)
        return SecretKeySpec(keyBytes, "DESede")
    }
}
