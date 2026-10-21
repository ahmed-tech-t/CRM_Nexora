package com.example.crm_nexora.domain.repo.local

import com.example.crm_nexora.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserLocalRepo {
    fun getUser(): Flow<User>
    suspend fun updateUser(user: User)
    suspend fun saveAuthSession(user: User)
}