package com.example.crm_nexora.data.repo

import androidx.datastore.core.DataStore
import com.example.crm_nexora.data.local.datastore.model.AppSettingsDS
import com.example.crm_nexora.data.mapper.toAppSettings
import com.example.crm_nexora.domain.models.local.AppSettings
import com.example.crm_nexora.domain.repo.local.AppSettingsRepo
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSettingsDSImpl @Inject constructor(
    private val dataStore: DataStore<AppSettingsDS>
) : AppSettingsRepo {



    override suspend fun updateLanguage(language: String) {
        dataStore.updateData { it.copy(language = language) }
    }



    override fun getSettings(): Flow<AppSettings> {
        return dataStore.data.map { it.toAppSettings() }
    }


    override suspend fun setLanguage(value: String) {
        updateLanguage(value)
    }
}