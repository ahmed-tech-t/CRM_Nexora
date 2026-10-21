package com.example.crm_nexora.ui.root

sealed interface RootEvents {
    data object RetryBootstrap : RootEvents
    data object OnNotificationTapped : RootEvents
}