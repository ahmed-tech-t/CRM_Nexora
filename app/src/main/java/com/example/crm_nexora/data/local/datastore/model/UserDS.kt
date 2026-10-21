package com.example.crm_nexora.data.local.datastore.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDS(
    val id: String?= null,
    val displayName: String="",
    val roleTitle: String="",
    val specialty: String = "",
    val employeeCode: String = "",
    val deviceId: String = "",
    val initials: String = "",
)
