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
import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.ui.screens.home.main.HomeScreen
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsScreen
import com.example.crm_nexora.ui.screens.statics.main.StaticsScreen

fun NavGraphBuilder.mainTabNav(
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    navigationUp: () -> Unit,
) {
    composable<AppRoute.Home> {
        HomeScreen(
            modifier = Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState,
            onNavigate = onNavigate,
            navigationUp = navigationUp,
        )
    }
    composable<AppRoute.Statics> {
        StaticsScreen(
            modifier = Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState,
            onNavigate = onNavigate,
            navigationUp = navigationUp,
        )
    }
    composable<AppRoute.Visits> {
        PlaceholderScreen(
            title = stringResource(R.string.nav_visits),
            modifier = Modifier.padding(innerPadding),
        )
    }
    composable<AppRoute.Plan> {
        PlaceholderScreen(
            title = stringResource(R.string.nav_plan),
            modifier = Modifier.padding(innerPadding),
        )
    }
    composable<AppRoute.Custody> {
        SampleAndGiftsScreen(
            modifier = Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState,
            onNavigate = onNavigate,
            navigationUp = navigationUp,
        )
    }
}
