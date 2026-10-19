package com.example.crm_nexora.ui.screens.datacenter.main

sealed interface DataCenterEvents {
    data object OnBack : DataCenterEvents
    data class OnSync(val categoryId: String) : DataCenterEvents
}
