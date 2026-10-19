package com.example.crm_nexora.ui.screens.sampleandgifts.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.screens.sampleandgifts.components.SampleAndGiftsUi
import com.example.crm_nexora.ui.utils.HandleUiEvents

@Composable
fun SampleAndGiftsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    navigationUp: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: SampleAndGiftsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.uiEvent.HandleUiEvents(
        snackbarHostState = snackbarHostState,
        onNavigate = onNavigate,
        navigationUp = navigationUp,
    )

    SampleAndGiftsUi(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier,
    )
}
