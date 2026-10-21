package com.example.crm_nexora.data.local.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class CryptoManager {

    companion object {
        private const val KEY_ALIAS = "crm_nexora_aes_key"
        private const val KEYSTORE = "AndroidKeyStore"
    }

    private val secretKey: SecretKey = getOrCreateKey()

    private fun getOrCreateKey(): SecretKey {
        val keyStore = KeyStore.getInstance(KEYSTORE).apply { load(null) }

        // Reuse existing key on later app launches
        if (keyStore.containsAlias(KEY_ALIAS)) {
            return keyStore.getKey(KEY_ALIAS, null) as SecretKey
        }

        // 1. Get KeyGenerator for AES in Android Keystore
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,  // "AES"
            KEYSTORE                          // "AndroidKeyStore"
        )

        // 2. Configure key properties (must match your Cipher: AES/GCM/NoPadding)
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()
        )

        // 3. Generate and store the key in Keystore
        return keyGenerator.generateKey()
    }

    fun encrypt(plainBytes: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        return iv + cipher.doFinal(plainBytes)
    }

    fun decrypt(cipherBytes: ByteArray): ByteArray {
        val iv = cipherBytes.copyOfRange(0, 12)
        val encrypted = cipherBytes.copyOfRange(12, cipherBytes.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
        return cipher.doFinal(encrypted)
    }
}