package com.example.crm_nexora.ui.screens.unplannedvisit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.ui.components.primitives.NexoraOutlinedSurface
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.widgets.NexoraSearchableDropdown
import com.example.crm_nexora.ui.components.widgets.SearchableOptionUi
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitState
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun EntityStepContent(
    state: UnplannedVisitState,
    onEvent: (UnplannedVisitEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dateTimeLabel = remember(state.visit.date, state.visit.time) {
        val date = state.visit.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val hourMin = state.visit.time.format(DateTimeFormatter.ofPattern("hh:mm", Locale.US))
        val period = if (state.visit.time.hour < 12) "ص" else "م"
        "$date • $hourMin $period"
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
    ) {
        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXxs),
                ) {
                    NexoraText(
                        text = stringResource(R.string.unplanned_datetime_label),
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = NexoraTextPrimary,
                    )
                    NexoraText(
                        text = stringResource(R.string.unplanned_datetime_hint),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = NexoraTextMuted,
                    )
                }
                NexoraText(
                    text = dateTimeLabel,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = NexoraTextPrimary,
                    modifier = Modifier
                        .background(NexoraSurfaceChip, RoundedCornerShape(NexoraDimens.radiusMd))
                        .padding(
                            horizontal = NexoraDimens.spaceMd,
                            vertical = NexoraDimens.spaceSm,
                        ),
                )
            }
        }

        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd)) {
                NexoraText(
                    text = stringResource(R.string.unplanned_section_location),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = NexoraTextPrimary,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
                ) {
                    EntitySelectDropdown(
                        label = stringResource(R.string.unplanned_label_city),
                        value = state.visit.city,
                        options = state.cityOptions,
                        onSelect = { onEvent(UnplannedVisitEvents.SelectCity(it)) },
                        modifier = Modifier.weight(1f),
                        showSearch = false,
                    )
                    EntitySelectDropdown(
                        label = stringResource(R.string.unplanned_label_district),
                        value = state.visit.region,
                        options = state.districtOptions,
                        onSelect = { onEvent(UnplannedVisitEvents.SelectDistrict(it)) },
                        modifier = Modifier.weight(1f),
                        showSearch = false,
                    )
                }
            }
        }

        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            EntitySelectDropdown(
                label = stringResource(R.string.unplanned_label_entity_type),
                value = state.visit.account,
                options = state.entityTypeOptions,
                onSelect = { onEvent(UnplannedVisitEvents.SelectEntityType(it)) },
                showSearch = true,
            )
        }

        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm)) {
                NexoraText(
                    text = stringResource(R.string.unplanned_section_place_specialty),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = NexoraTextPrimary,
                )
                NexoraText(
                    text = stringResource(R.string.unplanned_place_list_hint),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    color = NexoraTextMuted,
                )
                EntitySelectDropdown(
                    label = stringResource(R.string.unplanned_place_select_hint),
                    value = state.visit.placeName,
                    options = state.placeOptions,
                    onSelect = { onEvent(UnplannedVisitEvents.SelectPlace(it)) },
                    showSearch = false,
                    midTrailing = {
                        if (state.visit.classCategory.isNotBlank()) {
                            ClassBadge(
                                text = stringResource(
                                    R.string.unplanned_class_format,
                                    state.visit.classCategory,
                                ),
                            )
                        }
                    },
                )
                EntitySelectDropdown(
                    label = stringResource(R.string.unplanned_specialty_select_hint),
                    value = state.visit.specialty,
                    options = state.specialtyOptions,
                    onSelect = { onEvent(UnplannedVisitEvents.SelectSpecialty(it)) },
                    showSearch = false,
                )
            }
        }

        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            SegmentedChoiceRow(
                label = stringResource(R.string.unplanned_label_shift),
                options = listOf(
                    stringResource(R.string.shift_am) to (state.visit.shift == Shift.Am),
                    stringResource(R.string.shift_pm) to (state.visit.shift == Shift.Pm),
                ),
                onSelectIndex = { index ->
                    onEvent(
                        UnplannedVisitEvents.SelectShift(
                            if (index == 0) Shift.Am else Shift.Pm,
                        ),
                    )
                },
            )
        }

        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd)) {
                SegmentedChoiceRow(
                    label = stringResource(R.string.visit_type_label),
                    options = listOf(
                        stringResource(R.string.visit_type_single) to !state.visit.isDoubleVisit,
                        stringResource(R.string.visit_type_double) to state.visit.isDoubleVisit,
                    ),
                    onSelectIndex = { index ->
                        onEvent(UnplannedVisitEvents.SelectDoubleVisit(isDouble = index == 1))
                    },
                )
                if (state.visit.isDoubleVisit) {
                    EntitySelectDropdown(
                        label = stringResource(R.string.unplanned_label_supervisor),
                        value = state.visit.supervisorName.ifBlank { "—" },
                        options = state.supervisorOptions,
                        onSelect = { onEvent(UnplannedVisitEvents.SelectSupervisor(it)) },
                        showSearch = false,
                    )
                }
            }
        }
    }
}

@Composable
private fun EntitySelectDropdown(
    label: String,
    value: String,
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    showSearch: Boolean,
    midTrailing: (@Composable () -> Unit)? = null,
) {
    val state = rememberTextFieldState(initialText = value)
    LaunchedEffect(value) {
        if (state.text.toString() != value) {
            state.setTextAndPlaceCursorAtEnd(value)
        }
    }
    NexoraSearchableDropdown(
        state = state,
        options = options.map { SearchableOptionUi(id = it, label = it) },
        selectedId = value,
        onSelect = onSelect,
        modifier = modifier,
        label = label,
        readOnly = true,
        containerColor = NexoraSurfaceChip,
        shape = RoundedCornerShape(NexoraDimens.radiusMd),
        showDividers = false,
        showDefaultLeading = showSearch,
        midTrailing = midTrailing,
    )
}

@Preview(showBackground = true, locale = "ar", heightDp = 980)
@Composable
private fun EntityStepContentPreview() {
    CRM_NexoraTheme {
        EntityStepContent(
            state = UnplannedVisitViewModel.initialState(),
            onEvent = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
