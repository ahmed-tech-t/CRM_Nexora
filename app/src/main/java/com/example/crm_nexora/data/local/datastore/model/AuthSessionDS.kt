package com.example.crm_nexora.data.local.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthSessionDS(
    val accessToken: String = "",
    val refreshToken: String = "",
    val guestToken: String = "",
    )