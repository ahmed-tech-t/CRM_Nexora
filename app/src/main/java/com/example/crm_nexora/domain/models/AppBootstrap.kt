package com.example.crm_nexora.domain.models

import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.navigation.Graph

data class AppBootstrap(
    val language: String,
    val isAuthenticated: Boolean,
    val showOnBoarding: Boolean,
    val user: User?,
    val startGraph: Graph,
)