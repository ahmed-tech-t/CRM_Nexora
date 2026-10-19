package com.example.crm_nexora.ui.screens.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.navigation.AppRoute
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
    val selectedTabId = AppRoute.tabPathFromRoute(navBackStackEntry?.destination?.route)

    MainShell(
        modifier = modifier,
        selectedTabId = selectedTabId,
        onTabSelected = { tabId ->
            tabNavController.navigate(tabId) {
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
            startDestination = AppRoute.Home.path,
        ) {
            mainTabNav(
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState,
                onNavigate = { event ->
                    val isTab = AppRoute.tabRoutes.any { it.path == event.route }
                    if (isTab) {
                        tabNavController.navigate(event.route) {
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
