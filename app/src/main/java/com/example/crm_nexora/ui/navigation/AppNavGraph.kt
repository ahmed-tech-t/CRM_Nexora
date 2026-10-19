package com.example.crm_nexora.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crm_nexora.ui.screens.main.MainShellScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    NavHost(
        navController = navController,
        startDestination = AppRoute.Main.path,
        modifier = modifier,
    ) {
        composable(AppRoute.Main.path) {
            MainShellScreen(
                snackbarHostState = snackbarHostState,
                onNavigate = { event -> navController.navigate(event.route) },
                navigationUp = { navController.popBackStack() },
            )
        }
    }
}
