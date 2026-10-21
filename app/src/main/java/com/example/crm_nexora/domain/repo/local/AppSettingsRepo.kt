package com.example.crm_nexora.domain.repo.local

import com.example.crm_nexora.domain.models.local.AppSettings
import kotlinx.coroutines.flow.Flow

interface AppSettingsRepo {

    suspend fun updateLanguage(language: String)

    fun getSettings(): Flow<AppSettings>
    suspend fun setLanguage(value: String)
}