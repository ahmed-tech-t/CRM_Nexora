package com.example.crm_nexora.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe Navigation Compose destinations (kotlinx.serialization).
 * Tab [tabId] values match [com.example.crm_nexora.ui.components.chrome.defaultBottomNavItems] ids.
 */
@Serializable
sealed interface AppRoute {
    @Serializable
    data object Main : AppRoute

    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Stats : AppRoute

    @Serializable
    data object Visits : AppRoute

    @Serializable
    data object Plan : AppRoute

    @Serializable
    data object Custody : AppRoute

    @Serializable
    data object DataCenter : AppRoute

    companion object {
        val tabRoutes: List<AppRoute> = listOf(Home, Stats, Visits, Plan, Custody)

        fun fromTabId(tabId: String): AppRoute = when (tabId) {
            "home" -> Home
            "stats" -> Stats
            "visits" -> Visits
            "plan" -> Plan
            "custody" -> Custody
            else -> Home
        }

        fun tabId(route: AppRoute): String = when (route) {
            Home -> "home"
            Stats -> "stats"
            Visits -> "visits"
            Plan -> "plan"
            Custody -> "custody"
            Main, DataCenter -> "home"
        }
    }
}
