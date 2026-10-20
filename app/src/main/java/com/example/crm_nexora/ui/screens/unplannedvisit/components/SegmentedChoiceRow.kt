package com.example.crm_nexora.ui.screens.unplannedvisit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

/**
 * Figma 129:184 — one row: label | track.
 * Track = SurfaceChip container; selected = Primary inner pill.
 */
@Composable
fun SegmentedChoiceRow(
    label: String,
    options: List<Pair<String, Boolean>>,
    onSelectIndex: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (label.isNotBlank()) {
            NexoraText(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                ),
                color = NexoraTextPrimary,
            )
        }
        Row(
            modifier = Modifier
                .background(NexoraSurfaceChip, RoundedCornerShape(NexoraDimens.radiusMd))
                .padding(NexoraDimens.spaceXxs),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            options.forEachIndexed { index, (text, selected) ->
                Box(
                    modifier = Modifier
                        .background(
                            if (selected) NexoraPrimary else Color.Transparent,
                            RoundedCornerShape(NexoraDimens.radiusSm),
                        )
                        .clickable { onSelectIndex(index) }
                        .padding(
                            horizontal = NexoraDimens.spaceMd,
                            vertical = NexoraDimens.spaceXs,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    NexoraText(
                        text = text,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        ),
                        color = if (selected) Color.White else NexoraTextSecondary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun SegmentedChoiceRowPreview() {
    CRM_NexoraTheme {
        SegmentedChoiceRow(
            label = "فترة العمل",
            options = listOf(
                "صباحي" to false,
                "مسائي" to true,
            ),
            onSelectIndex = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
