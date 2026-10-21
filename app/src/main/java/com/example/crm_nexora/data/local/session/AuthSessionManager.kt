package com.example.crm_nexora.data.local.session

import android.util.Log
import com.example.crm_nexora.domain.repo.AuthSessionLocalRepo

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

private const val TAG = "AuthSessionManager"

@Singleton
class AuthSessionManager @Inject constructor(
    private val authSessionLocalRepo: AuthSessionLocalRepo,
) {
    @Volatile
    private var cachedAccess = ""

    @Volatile
    private var cachedRefresh = ""


    private val _sessionExpired = MutableSharedFlow<String?>(extraBufferCapacity = 1)
    val sessionExpired: SharedFlow<String?> = _sessionExpired.asSharedFlow()
    suspend fun initialize() {
        cachedAccess = authSessionLocalRepo.getAccessToken()
        cachedRefresh = authSessionLocalRepo.getRefreshToken()
    }

    suspend fun saveSession(access: String, refresh: String) {
        Log.d(TAG, "saveSession: token saved successfully")
        authSessionLocalRepo.saveSession(access, refresh)
        cachedAccess = access
        cachedRefresh = refresh
    }

    suspend fun onSessionExpired(message: String? = null) {
        clearSession()
        _sessionExpired.emit(message)
    }

    suspend fun clearSession() {
        Log.d(TAG, "clearSession: token cleared successfully")
        authSessionLocalRepo.clearSession()
        cachedAccess = ""
        cachedRefresh = ""

    }


    fun isAuthenticated() = cachedAccess.isNotBlank()



    fun getAccessTokenSync() = cachedAccess
    fun getRefreshTokenSync() = cachedRefresh


}