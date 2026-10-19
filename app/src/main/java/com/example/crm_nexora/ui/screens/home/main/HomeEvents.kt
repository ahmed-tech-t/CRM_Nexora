package com.example.crm_nexora.ui.screens.home.main

import com.example.crm_nexora.ui.components.chrome.nav_drawer.NavDrawerItem

sealed interface HomeEvents {
    data object OnMenuClick : HomeEvents
    data object OnDrawerDismiss : HomeEvents
    data class OnDrawerItem(val item: NavDrawerItem) : HomeEvents
    data object OnStartShift : HomeEvents
    data object OnEndShift : HomeEvents
    data object OnSyncClick : HomeEvents
    data object OnActiveContinue : HomeEvents
    data object OnActiveCancel : HomeEvents
    data class OnStartVisit(val visitId: String) : HomeEvents
    data object OnTodayMoreClick : HomeEvents
}
