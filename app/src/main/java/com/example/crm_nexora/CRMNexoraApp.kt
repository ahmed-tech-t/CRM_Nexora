package com.example.crm_nexora

import android.app.Application
import com.example.crm_nexora.data.remote.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject

@HiltAndroidApp
class CRMNexoraApp : Application(){
    @Inject
    lateinit var localeHelper: LocaleHelper

    override fun onCreate() {
        super.onCreate()
        localeHelper.applySavedLocale(this)

    }
}
