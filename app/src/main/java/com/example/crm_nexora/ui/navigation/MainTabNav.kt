package com.example.crm_nexora.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.screens.home.main.HomeScreen

fun NavGraphBuilder.mainTabNav(
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    navigationUp: () -> Unit,
) {
    composable(AppRoute.Home.path) {
        HomeScreen(
            modifier = Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState,
            onNavigate = onNavigate,
            navigationUp = navigationUp,
        )
    }
    composable(AppRoute.Stats.path) {
        PlaceholderScreen(
            title = stringResource(R.string.nav_stats),
            modifier = Modifier.padding(innerPadding),
        )
    }
    composable(AppRoute.Visits.path) {
        PlaceholderScreen(
            title = stringResource(R.string.nav_visits),
            modifier = Modifier.padding(innerPadding),
        )
    }
    composable(AppRoute.Plan.path) {
        PlaceholderScreen(
            title = stringResource(R.string.nav_plan),
            modifier = Modifier.padding(innerPadding),
        )
    }
    composable(AppRoute.Custody.path) {
        PlaceholderScreen(
            title = stringResource(R.string.nav_custody),
            modifier = Modifier.padding(innerPadding),
        )
    }
}
