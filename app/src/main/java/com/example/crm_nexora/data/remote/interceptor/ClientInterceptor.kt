package com.example.crm_nexora.data.remote.interceptor

import com.example.crm_nexora.data.local.AppSettingsManager
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor(
    private val appSettingsManager: AppSettingsManager,
    private val deviceId: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            addHeader("Accept", "application/json")
            addHeader("language", appSettingsManager.getLanguageSync())
            addHeader("original", "android")
            addHeader("device-id", deviceId)
        }.build()
        return chain.proceed(request)
    }
}