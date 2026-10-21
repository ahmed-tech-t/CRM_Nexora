package com.example.crm_nexora.ui.screens.home.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.components.chrome.LocalScaffoldController
import com.example.crm_nexora.ui.components.chrome.notchesPadding
import com.example.crm_nexora.ui.screens.home.components.HomeAttendanceHeader
import com.example.crm_nexora.ui.utils.HandleUiEvents

@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    navigationUp: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scaffoldController = LocalScaffoldController.current
    viewModel.uiEvent.HandleUiEvents(
        snackbarHostState = snackbarHostState,
        onNavigate = onNavigate,
        navigationUp = navigationUp,
    )
    LaunchedEffect(true) {

        scaffoldController.updateScaffold(
            topBar = {
                HomeAttendanceHeader(
                    modifier = Modifier.notchesPadding(),
                    user = state.user,
                    attendance = state.attendance,
                    connection = state.connection,
                    onStartShift = { viewModel.onEvent(HomeEvents.OnStartShift) },
                    onEndShift = { viewModel.onEvent(HomeEvents.OnEndShift) },
                )
            }
        )
    }

    HomeUi(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier,
    )
}
