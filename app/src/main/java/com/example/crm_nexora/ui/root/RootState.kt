package com.propertyadvisor.propertyadvisor.ui.screens.root.main

import androidx.compose.runtime.Immutable
import com.example.crm_nexora.domain.models.AppBootstrap
import com.example.crm_nexora.domain.utils.Resource

@Immutable
data class RootState(
    val bootstrap: Resource<AppBootstrap> = Resource.Loading(),
)