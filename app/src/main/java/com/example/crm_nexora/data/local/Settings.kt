package com.example.crm_nexora.data.local

import com.example.crm_nexora.domain.repo.local.AppSettingsRepo
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.first

@Singleton
class AppSettingsManager @Inject constructor(
    private val appSettingsRepo: AppSettingsRepo,
) {
    @Volatile
    private var language = "en"


    suspend fun initialize() {
        val settings = appSettingsRepo.getSettings().first()
        language = settings.language
    }

    suspend fun updateLanguage(value: String) {
        appSettingsRepo.updateLanguage(value)
        language = value
    }


    fun getLanguageSync() = language
}