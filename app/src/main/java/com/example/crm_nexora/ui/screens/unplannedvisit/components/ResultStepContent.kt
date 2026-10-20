package com.example.crm_nexora.ui.screens.unplannedvisit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraOutlinedSurface
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.mapper.resolvedDisplayName
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitState
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitStep
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraBorderFocus
import com.example.crm_nexora.ui.theme.NexoraDanger
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraPrimaryDeep
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary
import com.example.crm_nexora.ui.theme.NexoraWarningBorder
import com.example.crm_nexora.ui.theme.NexoraWarningDeep
import com.example.crm_nexora.ui.theme.NexoraWarningSurface

@Composable
fun ResultStepContent(
    state: UnplannedVisitState,
    onEvent: (UnplannedVisitEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visit = state.visit
    val specialtyShort = visit.specialty.substringBefore(' ').ifBlank { visit.specialty }
    val displayName = if (specialtyShort.isNotBlank()) {
        stringResource(
            R.string.unplanned_summary_name_format,
            visit.resolvedDisplayName(),
            specialtyShort
        )
    } else {
        visit.resolvedDisplayName()
    }
    val locationLine = buildString {
        val base = visit.address.ifBlank {
            listOf(visit.city, visit.region).filter { it.isNotBlank() }.joinToString(" - ")
        }
        append(base)
        if (visit.classCategory.isNotBlank()) {
            append(" • ")
            append(stringResource(R.string.unplanned_class_format, visit.classCategory))
        }
    }
    val visitTypeValue = buildString {
        if (visit.isDoubleVisit && visit.supervisorName.isNotBlank()) {
            append("(")
            append(visit.supervisorName)
            append(") ")
        }
        append(
            stringResource(
                if (visit.isDoubleVisit) R.string.visit_type_double else R.string.visit_type_single,
            ),
        )
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
    ) {
        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NexoraText(
                        text = stringResource(R.string.unplanned_summary_title),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                        ),
                        color = NexoraTextFaint,
                        modifier = Modifier.weight(1f),
                    )
                    Button(
                        onClick = { onEvent(UnplannedVisitEvents.ViewLocationClick) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NexoraDanger,
                            contentColor = Color.White,
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            horizontal = NexoraDimens.spaceMd,
                            vertical = NexoraDimens.spaceSm,
                        ),
                        shape = RoundedCornerShape(NexoraDimens.radiusPill),
                    ) {
                        NexoraText(
                            text = stringResource(R.string.unplanned_view_location),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                            ),
                            color = Color.White,
                        )
                    }
                }

                // Nested entity card (Figma 82:452)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            NexoraDimens.borderThin,
                            NexoraBorder,
                            RoundedCornerShape(NexoraDimens.radiusLg)
                        )
                        .background(NexoraSurface, RoundedCornerShape(NexoraDimens.radiusLg))
                        .padding(NexoraDimens.spaceSection),
                    verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
                ) {
                    Column {
                        NexoraText(
                            text = displayName,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.5.sp,
                            ),
                            color = NexoraTextPrimary,
                        )

                        NexoraText(
                            text = locationLine,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                            ),
                            color = NexoraTextMuted,
                        )
                    }

                    SummaryMetaRow(
                        label = stringResource(R.string.unplanned_summary_shift_label),
                        value = stringResource(
                            if (visit.shift == Shift.Am) R.string.shift_am else R.string.shift_pm,
                        ),
                    )
                    SummaryMetaRow(
                        label = stringResource(R.string.unplanned_summary_visit_type_label),
                        value = visitTypeValue,
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXs),
                ) {
                    SummaryCountChip(
                        text = stringResource(
                            R.string.unplanned_summary_gifts_format,
                            visit.giftsQty,
                        ),
                        container = NexoraWarningSurface,
                        border = NexoraWarningBorder.copy(alpha = 0.6f),
                        content = NexoraWarningDeep,
                        modifier = Modifier.weight(1f),
                    )
                    SummaryCountChip(
                        text = stringResource(
                            R.string.unplanned_summary_products_format,
                            visit.productsQty,
                        ),
                        container = NexoraSurfaceFocus,
                        border = NexoraBorderFocus.copy(alpha = 0.6f),
                        content = NexoraPrimaryDeep,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }

        NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd)) {
                NexoraText(
                    text = stringResource(R.string.unplanned_notes_label),
                    style = MaterialTheme.typography.labelMedium,
                    color = NexoraTextPrimary,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            NexoraDimens.borderThin,
                            NexoraBorder,
                            RoundedCornerShape(NexoraDimens.radiusMd),
                        )
                        .background(NexoraSurfaceMuted, RoundedCornerShape(NexoraDimens.radiusMd))
                        .padding(NexoraDimens.spaceMd),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NexoraText(
                        text = stringResource(R.string.unplanned_voice_prompt),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            lineHeight = 16.5.sp,
                        ),
                        color = NexoraTextMuted,
                        modifier = Modifier.weight(1f),
                    )
                    NexoraButton(
                        text = stringResource(R.string.unplanned_voice_note),
                        onClick = { onEvent(UnplannedVisitEvents.VoiceNoteClick) },
                        variant = NexoraButtonVariant.Navy,
                        fillMaxWidth = false,
                        compact = true,
                        textStyle = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            lineHeight = 16.5.sp,
                        ),
                        leadingIcon = {
                            NexoraIcon(
                                painter = painterResource(R.drawable.ic_mic),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(NexoraDimens.iconSizeSm),
                            )
                        },
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    NexoraDimens.borderThin,
                    NexoraBorder,
                    RoundedCornerShape(NexoraDimens.radiusLg),
                )
                .background(NexoraSurface, RoundedCornerShape(NexoraDimens.radiusLg))
                .clickable { onEvent(UnplannedVisitEvents.AttachClick) }
                .padding(NexoraDimens.spaceMd),
            horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(NexoraDimens.attachBoxSize)
                    .border(
                        NexoraDimens.borderThin,
                        NexoraBorder,
                        RoundedCornerShape(NexoraDimens.radiusMd)
                    )
                    .background(NexoraSurfaceMuted, RoundedCornerShape(NexoraDimens.radiusMd)),
                contentAlignment = Alignment.Center,
            ) {
                NexoraIcon(
                    painter = painterResource(R.drawable.ic_image),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(NexoraDimens.iconSizeLg),
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                NexoraText(
                    text = stringResource(R.string.unplanned_attach_title),
                    style = MaterialTheme.typography.labelMedium,
                    color = NexoraTextPrimary,
                )
                NexoraText(
                    text = stringResource(R.string.unplanned_attach_hint),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                    ),
                    color = NexoraTextMuted,
                )
            }
            Box(
                modifier = Modifier
                    .size(
                        width = NexoraDimens.attachPillWidth,
                        height = NexoraDimens.radiusPill,
                    )
                    .background(NexoraNavy, RoundedCornerShape(NexoraDimens.radiusSm)),
                contentAlignment = Alignment.Center,
            ) {
                NexoraIcon(
                    painter = painterResource(R.drawable.ic_attach),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(NexoraDimens.iconSizeSm),
                )
            }
        }
    }
}

@Composable
private fun SummaryMetaRow(
    label: String,
    value: String,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(color = NexoraSurfaceChip, thickness = NexoraDimens.borderThin)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = NexoraDimens.spaceSm),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NexoraText(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                ),
                color = NexoraTextSecondary,
            )
            Box(
                modifier = Modifier
                    .background(NexoraSurfaceChip, RoundedCornerShape(NexoraDimens.radiusSm))
                    .padding(NexoraDimens.borderFocus),
            ) {
                NexoraText(
                    text = value,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.5.sp,
                    ),
                    color = NexoraPrimaryDeep,
                    modifier = Modifier
                        .background(
                            NexoraSurfaceFocus,
                            RoundedCornerShape(NexoraDimens.radiusTight)
                        )
                        .padding(
                            horizontal = NexoraDimens.space10,
                            vertical = NexoraDimens.spaceXxs,
                        ),
                )
            }
        }
    }
}

@Composable
private fun SummaryCountChip(
    text: String,
    container: Color,
    border: Color,
    content: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(NexoraDimens.borderThin, border, RoundedCornerShape(NexoraDimens.radiusSm))
            .background(container, RoundedCornerShape(NexoraDimens.radiusSm))
            .padding(vertical = NexoraDimens.spaceXs),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
            ),
            color = content,
        )
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 860)
@Composable
private fun ResultStepContentPreview() {
    CRM_NexoraTheme {
        ResultStepContent(
            state = UnplannedVisitViewModel.initialState().copy(
                currentStep = UnplannedVisitStep.Result,
            ),
            onEvent = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
