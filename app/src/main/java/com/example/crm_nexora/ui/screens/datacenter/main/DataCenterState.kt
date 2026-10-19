package com.example.crm_nexora.ui.screens.datacenter.main

import androidx.compose.runtime.Immutable
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncItemUi

@Immutable
data class DataCenterState(
    val items: List<DataCenterSyncItemUi>,
)
