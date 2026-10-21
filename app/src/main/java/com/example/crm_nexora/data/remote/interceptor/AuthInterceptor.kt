package com.example.crm_nexora.data.remote.interceptor

import com.example.crm_nexora.data.local.session.AuthSessionManager
import jakarta.inject.Inject
import jakarta.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response


@Singleton
class AuthInterceptor @Inject constructor(
    private val authSessionManager: AuthSessionManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val access = authSessionManager.getAccessTokenSync()
        val request = chain.request().newBuilder().apply {
            header("Authorization", "Bearer $access")
        }.build()
        return chain.proceed(request)
    }
}