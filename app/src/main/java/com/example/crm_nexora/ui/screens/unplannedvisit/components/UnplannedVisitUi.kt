package com.example.crm_nexora.ui.screens.unplannedvisit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.chrome.AppHeader
import com.example.crm_nexora.ui.components.chrome.ProgressSteps
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitState
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitStep
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraSurface

@Composable
fun UnplannedVisitUi(
    state: UnplannedVisitState,
    onEvent: (UnplannedVisitEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stepIndex = when (state.currentStep) {
        UnplannedVisitStep.Entity -> 0
        UnplannedVisitStep.GiftsProducts -> 1
        UnplannedVisitStep.Result -> 2
    }
    val titleRes = when (state.currentStep) {
        UnplannedVisitStep.Entity -> R.string.unplanned_visit_title
        UnplannedVisitStep.GiftsProducts -> R.string.step_gifts_products
        UnplannedVisitStep.Result -> R.string.unplanned_visit_data_title
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NexoraSurface),
    ) {
        AppHeader(
            title = stringResource(titleRes),
            showBack = true,
            onBack = { onEvent(UnplannedVisitEvents.Back) },
        )
        Column(modifier = Modifier.fillMaxWidth().background(NexoraSurface)) {
            ProgressSteps(
                steps = listOf(
                    stringResource(R.string.step_entity_data),
                    stringResource(R.string.step_gifts_products),
                    stringResource(R.string.step_result_finish),
                ),
                currentIndex = stepIndex,
            )
            HorizontalDivider(color = NexoraBorder, thickness = NexoraDimens.borderThin)
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(NexoraDimens.spaceLg),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            when (state.currentStep) {
                UnplannedVisitStep.Entity -> EntityStepContent(state = state, onEvent = onEvent)
                UnplannedVisitStep.GiftsProducts -> GiftsProductsStepContent(
                    state = state,
                    onEvent = onEvent,
                )
                UnplannedVisitStep.Result -> ResultStepContent(state = state, onEvent = onEvent)
            }
        }

        FooterBar(state = state, onEvent = onEvent)
    }

    AddVisitItemSheetHost(state = state, onEvent = onEvent)
}

@Composable
private fun FooterBar(
    state: UnplannedVisitState,
    onEvent: (UnplannedVisitEvents) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NexoraSurface)
            .padding(NexoraDimens.spaceLg),
        horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
    ) {
        when (state.currentStep) {
            UnplannedVisitStep.Entity -> {
                NexoraButton(
                    text = stringResource(R.string.action_next),
                    onClick = { onEvent(UnplannedVisitEvents.Next) },
                    variant = NexoraButtonVariant.Primary,
                )
            }
            UnplannedVisitStep.GiftsProducts -> {
                NexoraButton(
                    text = stringResource(R.string.action_previous),
                    onClick = { onEvent(UnplannedVisitEvents.Previous) },
                    variant = NexoraButtonVariant.Secondary,
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                )
                NexoraButton(
                    text = stringResource(R.string.action_next),
                    onClick = { onEvent(UnplannedVisitEvents.Next) },
                    variant = NexoraButtonVariant.Primary,
                    modifier = Modifier.weight(2f),
                    fillMaxWidth = true,
                )
            }
            UnplannedVisitStep.Result -> {
                NexoraButton(
                    text = stringResource(R.string.action_previous),
                    onClick = { onEvent(UnplannedVisitEvents.Previous) },
                    variant = NexoraButtonVariant.Secondary,
                    modifier = Modifier.weight(1f),
                    fillMaxWidth = true,
                )

                NexoraButton(
                    text = stringResource(R.string.unplanned_visit_end),
                    onClick = { onEvent(UnplannedVisitEvents.EndVisit) },
                    variant = NexoraButtonVariant.Primary,
                    modifier = Modifier.weight(2f),
                    fillMaxWidth = true,
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 900)
@Composable
private fun UnplannedVisitUiEntityPreview() {
    CRM_NexoraTheme {
        UnplannedVisitUi(
            state = UnplannedVisitViewModel.initialState(),
            onEvent = {},
        )
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 900)
@Composable
private fun UnplannedVisitUiGiftsPreview() {
    CRM_NexoraTheme {
        UnplannedVisitUi(
            state = UnplannedVisitViewModel.initialState().copy(
                currentStep = UnplannedVisitStep.GiftsProducts,
            ),
            onEvent = {},
        )
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 900)
@Composable
private fun UnplannedVisitUiResultPreview() {
    CRM_NexoraTheme {
        UnplannedVisitUi(
            state = UnplannedVisitViewModel.initialState().copy(
                currentStep = UnplannedVisitStep.Result,
            ),
            onEvent = {},
        )
    }
}
