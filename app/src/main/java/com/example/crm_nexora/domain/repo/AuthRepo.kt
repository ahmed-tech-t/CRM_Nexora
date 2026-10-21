package com.example.crm_nexora.domain.repo

import com.example.crm_nexora.domain.models.User
import com.example.crm_nexora.domain.utils.Resource

import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    fun loginWithEmail(email: String, password: String): Flow<Resource<User?>>


    fun logout(): Flow<Resource<Unit>>
}