package com.example.crm_nexora.data.mapper

import com.example.crm_nexora.data.local.datastore.model.UserDS
import com.example.crm_nexora.domain.models.User

fun UserDS.toUser(): User{
    return User(
        id = id.orEmpty(),
        displayName = displayName,
        roleTitle = roleTitle,
        specialty = specialty,
        employeeCode = employeeCode,
        deviceId = deviceId,
        initials = initials,
    )
}

fun User.toUserDS(): UserDS{
    return UserDS(
        id = id.orEmpty(),
        displayName = displayName,
        roleTitle = roleTitle,
        specialty = specialty,
        employeeCode = employeeCode,
        deviceId = deviceId,
        initials = initials,
    )
}
