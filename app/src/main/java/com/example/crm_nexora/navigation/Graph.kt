package com.example.crm_nexora.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Graph {
    @Serializable
    data object Main : Graph

    data object Auth : Graph

}