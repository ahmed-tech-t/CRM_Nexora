package com.example.crm_nexora.data.local.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.crm_nexora.data.local.crypto.CryptoManager
import com.example.crm_nexora.data.local.datastore.model.AuthSessionDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class AuthSessionDSSerializer (
    private val crypto: CryptoManager,
    private val json: Json
) : Serializer<AuthSessionDS> {

    override val defaultValue = AuthSessionDS()


    override suspend fun readFrom(input: InputStream): AuthSessionDS {
        return try {
            val cipherBytes = input.readBytes()
            if (cipherBytes.isEmpty()) return defaultValue

            val plainBytes = crypto.decrypt(cipherBytes)
            val decodedValue = plainBytes.decodeToString()

            json.decodeFromString(AuthSessionDS.serializer(), decodedValue)
        } catch (e: Exception) {
            throw CorruptionException("Cannot read auth session", e)
        }
    }

    override suspend fun writeTo(t: AuthSessionDS, output: OutputStream) {
        withContext(Dispatchers.IO) {
            val encodedValue = json.encodeToString(AuthSessionDS.serializer(), t)
            val cipherBytes = crypto.encrypt(encodedValue.encodeToByteArray())
            output.write(cipherBytes)
        }
    }
}