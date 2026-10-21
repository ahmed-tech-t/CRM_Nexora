package com.example.crm_nexora.data.remote.interceptor

import com.example.crm_nexora.data.local.session.AuthSessionManager

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class AccountDeactivatedInterceptor @Inject constructor(
    private val authSessionManager: AuthSessionManager,
    private val json: Json,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val contentType = response.body?.contentType()?.toString().orEmpty()
        if (!contentType.contains("json", ignoreCase = true)) return response

        val bodyString = runCatching {
            response.peekBody(PEEK_BYTES).string()
        }.getOrNull().orEmpty()
        if (bodyString.isBlank()) return response

//        val probe = runCatching {
//            json.decodeFromString<BaseResponse<JsonElement?>>(bodyString)
//        }.getOrNull() ?: return response

//        if (!probe.status && probe.code == ACCOUNT_DEACTIVATED_CODE) {
//            runBlocking { authSessionManager.onSessionExpired(probe.message) }
//        }
        return response
    }

    private companion object {
        const val PEEK_BYTES = 64L * 1024
    }
}
