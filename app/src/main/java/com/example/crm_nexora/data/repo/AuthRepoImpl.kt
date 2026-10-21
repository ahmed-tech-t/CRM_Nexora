package com.example.crm_nexora.data.repo

import com.example.crm_nexora.domain.models.User

import com.example.crm_nexora.domain.repo.AuthRepo
import com.example.crm_nexora.domain.utils.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class AuthRepoImpl @Inject constructor(

    private val json: Json
) : AuthRepo {


    override fun loginWithEmail(
        email: String,
        password: String
    ): Flow<Resource<User?>> {
      TODO()
    }


    override fun logout(): Flow<Resource<Unit>> {
     TODO()
    }

}