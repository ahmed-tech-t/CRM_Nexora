package com.example.crm_nexora.data.repo

import androidx.datastore.core.DataStore
import com.example.crm_nexora.data.local.datastore.model.UserDS
import com.example.crm_nexora.data.mapper.toUser
import com.example.crm_nexora.domain.models.User
import com.example.crm_nexora.domain.repo.local.UserLocalRepo

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDSRepoImpl @Inject constructor(
    private val dataStore: DataStore<UserDS>,
) : UserLocalRepo {
    override fun getUser(): Flow<User> {
        return dataStore.data.map { userDS ->
            userDS.toUser()
        }
    }

    override suspend fun updateUser(user: User) {
        dataStore.updateData { current ->
            current.copy(
                id = user.id,
                displayName = user.displayName,
                roleTitle = user.roleTitle,
                specialty = user.specialty,
                employeeCode = user.employeeCode,
                deviceId = user.deviceId,
                initials = user.initials,
            )
        }
    }



    override suspend fun saveAuthSession(user: User) {
        updateUser(user)
    }

}