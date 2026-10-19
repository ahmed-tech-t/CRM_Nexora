package com.example.crm_nexora.ui.screens.datacenter.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.screens.datacenter.components.DataCenterUi
import com.example.crm_nexora.ui.utils.HandleUiEvents

@Composable
fun DataCenterScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    navigationUp: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: DataCenterViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.uiEvent.HandleUiEvents(
        snackbarHostState = snackbarHostState,
        onNavigate = onNavigate,
        navigationUp = navigationUp,
    )

    DataCenterUi(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier,
    )
}
