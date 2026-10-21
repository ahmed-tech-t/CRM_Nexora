package com.example.crm_nexora.data.remote.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.crm_nexora.domain.repo.local.AppSettingsRepo
import com.example.crm_nexora.domain.utils.Constants.DEFAULT_LANG
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class LocaleHelper @Inject constructor(
    private val appSettingsRepo: AppSettingsRepo,
) {

    fun setLocale(context: Context, languageCode: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(languageCode),
        )
    }

    fun applySavedLocale(context: Context) {
        val languageCode = runBlocking {
            runCatching {
                appSettingsRepo.getSettings().first().language
            }.getOrDefault(DEFAULT_LANG)
        }
        setLocale(context, languageCode)
    }
}
