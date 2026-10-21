package com.example.crm_nexora.data.repo

import androidx.datastore.core.DataStore
import com.example.crm_nexora.data.local.datastore.model.AuthSessionDS
import com.example.crm_nexora.domain.repo.AuthSessionLocalRepo
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AuthSessionLocalRepoImpl @Inject constructor(
    private val dataStore: DataStore<AuthSessionDS>
) : AuthSessionLocalRepo {

    val session: Flow<AuthSessionDS> = dataStore.data

    override suspend fun getAccessToken(): String =
        dataStore.data.first().accessToken

    override suspend fun getRefreshToken(): String {
        return dataStore.data.first().refreshToken
    }

    override suspend fun saveSession(accessToken: String, refreshToken: String) {
        dataStore.updateData { current ->
            current.copy(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    override suspend fun clearSession() {
        dataStore.updateData { AuthSessionDS() }  // resets to empty defaults
    }

    override suspend fun updateToken(accessToken: String) {
        dataStore.updateData { current ->
            current.copy(
                accessToken = accessToken,
            )
        }
    }
}