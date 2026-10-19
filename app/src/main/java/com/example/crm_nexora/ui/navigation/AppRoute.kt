package com.example.crm_nexora.ui.navigation

/**
 * App routes. Tab paths match [com.example.crm_nexora.ui.components.chrome.defaultBottomNavItems] ids.
 */
sealed class AppRoute(val path: String) {
    /** Root shell that owns the shared bottom nav. */
    data object Main : AppRoute("main")

    data object Home : AppRoute("home")
    data object Stats : AppRoute("stats")
    data object Visits : AppRoute("visits")
    data object Plan : AppRoute("plan")
    data object Custody : AppRoute("custody")

    companion object {
        val tabRoutes: List<AppRoute> = listOf(Home, Stats, Visits, Plan, Custody)

        fun fromPath(path: String): AppRoute? = when (path) {
            Main.path -> Main
            Home.path -> Home
            Stats.path -> Stats
            Visits.path -> Visits
            Plan.path -> Plan
            Custody.path -> Custody
            else -> null
        }

        fun tabPathFromRoute(route: String?): String {
            if (route == null) return Home.path
            return tabRoutes.firstOrNull { route.contains(it.path) }?.path ?: Home.path
        }
    }
}
