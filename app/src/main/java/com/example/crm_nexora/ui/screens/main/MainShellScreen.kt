package com.example.crm_nexora.ui.screens.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.ui.navigation.mainTabNav

@Composable
fun MainShellScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    navigationUp: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val tabNavController = rememberNavController()
    val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectedTabId = when {
        currentDestination?.hierarchy?.any { it.hasRoute(AppRoute.Stats::class) } == true ->
            AppRoute.tabId(AppRoute.Stats)
        currentDestination?.hierarchy?.any { it.hasRoute(AppRoute.Visits::class) } == true ->
            AppRoute.tabId(AppRoute.Visits)
        currentDestination?.hierarchy?.any { it.hasRoute(AppRoute.Plan::class) } == true ->
            AppRoute.tabId(AppRoute.Plan)
        currentDestination?.hierarchy?.any { it.hasRoute(AppRoute.Custody::class) } == true ->
            AppRoute.tabId(AppRoute.Custody)
        else -> AppRoute.tabId(AppRoute.Home)
    }

    MainShell(
        modifier = modifier,
        selectedTabId = selectedTabId,
        onTabSelected = { tabId ->
            val destination = AppRoute.fromTabId(tabId)
            tabNavController.navigate(destination) {
                popUpTo(tabNavController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        snackbarHostState = snackbarHostState,
    ) { innerPadding ->
        NavHost(
            navController = tabNavController,
            startDestination = AppRoute.Home,
        ) {
            mainTabNav(
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState,
                onNavigate = { event ->
                    if (event.destination in AppRoute.tabRoutes) {
                        tabNavController.navigate(event.destination) {
                            popUpTo(tabNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    } else {
                        onNavigate(event)
                    }
                },
                navigationUp = navigationUp,
            )
        }
    }
}
