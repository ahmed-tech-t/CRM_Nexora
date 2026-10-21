package com.example.crm_nexora.domain.repo

interface AuthSessionLocalRepo {
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun saveSession(accessToken: String, refreshToken: String)

    suspend fun clearSession()
    suspend fun updateToken(accessToken: String)
}