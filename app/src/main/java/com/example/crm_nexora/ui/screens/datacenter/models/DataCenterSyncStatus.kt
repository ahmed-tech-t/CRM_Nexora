package com.example.crm_nexora.ui.screens.datacenter.models

sealed interface DataCenterSyncStatus {
    data object Idle : DataCenterSyncStatus
    data object Syncing : DataCenterSyncStatus
}
