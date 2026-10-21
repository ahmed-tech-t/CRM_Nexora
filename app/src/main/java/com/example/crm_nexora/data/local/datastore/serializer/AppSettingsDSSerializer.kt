package com.example.crm_nexora.data.local.datastore.serializer


import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.crm_nexora.data.local.datastore.model.AppSettingsDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class AppSettingsDSSerializer(
    private val json: Json
) :
    Serializer<AppSettingsDS> {
    override val defaultValue: AppSettingsDS
        get() = AppSettingsDS()

    override suspend fun readFrom(input: InputStream): AppSettingsDS {
        return try {
            json.decodeFromString(
                deserializer = AppSettingsDS.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("Unable to read prefs", e)
        }
    }

    override suspend fun writeTo(t: AppSettingsDS, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                json.encodeToString(
                    serializer = AppSettingsDS.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}