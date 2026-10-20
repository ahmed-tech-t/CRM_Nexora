package com.example.crm_nexora.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterScreen
import com.example.crm_nexora.ui.screens.main.MainShellScreen
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    NavHost(
        navController = navController,
        startDestination = AppRoute.Main,
        modifier = modifier,
    ) {
        composable<AppRoute.Main> {
            MainShellScreen(
                snackbarHostState = snackbarHostState,
                onNavigate = { event -> navController.navigate(event.destination) },
                navigationUp = { navController.popBackStack() },
            )
        }
        composable<AppRoute.DataCenter> {
            DataCenterScreen(
                snackbarHostState = snackbarHostState,
                onNavigate = { event -> navController.navigate(event.destination) },
                navigationUp = { navController.popBackStack() },
            )
        }
        composable<AppRoute.UnplannedVisit> {
            UnplannedVisitScreen(
                snackbarHostState = snackbarHostState,
                onNavigate = { event -> navController.navigate(event.destination) },
                navigationUp = { navController.popBackStack() },
            )
        }
    }
}
