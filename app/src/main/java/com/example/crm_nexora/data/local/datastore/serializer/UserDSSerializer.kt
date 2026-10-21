package com.example.crm_nexora.data.local.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.crm_nexora.data.local.datastore.model.UserDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class UserDSSerializer (
    private val json: Json
) : Serializer<UserDS> {
    override val defaultValue: UserDS
        get() = UserDS()

    override suspend fun readFrom(input: InputStream): UserDS {
        return try {
            json.decodeFromString(
                deserializer = UserDS.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("Unable to read prefs", e)
        }
    }

    override suspend fun writeTo(t: UserDS, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                json.encodeToString(
                    serializer = UserDS.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}