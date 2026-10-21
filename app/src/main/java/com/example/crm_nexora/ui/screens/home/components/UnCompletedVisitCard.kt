package com.example.crm_nexora.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.VisitPrimaryAction
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.widgets.visit.VisitDurationChip
import com.example.crm_nexora.ui.components.widgets.visit.VisitSpecialtyClassLine
import com.example.crm_nexora.ui.components.widgets.visit.VisitStatusDot
import com.example.crm_nexora.ui.components.widgets.visit.visitCardSurface
import com.example.crm_nexora.ui.mapper.resolvedPlaceName
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.models.StatusTone
import com.example.crm_nexora.ui.models.VisitUi
import com.example.crm_nexora.ui.preview.VisitSamples
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraStatusBlue
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

/** Child order is RTL start→end (first = right on screen). */
@Composable
fun UnCompletedVisitCard(
    model: VisitUi,
    onPrimaryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onCancel: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.visitCardSurface(
            elevation = 4.dp,
            contentPadding = NexoraDimens.spaceLg,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = NexoraDimens.spaceSm),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    VisitStatusDot(tone = StatusTone.Success)
                    NexoraText(
                        text = stringResource(R.string.visit_status_in_progress),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            lineHeight = 16.5.sp,
                        ),
                        color = NexoraSuccess,
                        textAlign = TextAlign.Start,
                    )
                }

                if (model.durationLabel.isNotBlank()) {
                    VisitDurationChip(label = model.durationLabel)
                } else {
                    Spacer(Modifier.width(1.dp))
                }
            }
            HorizontalDivider(Modifier.alpha(.2f))
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            NexoraText(
                text = model.resolvedPlaceName(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                ),
                color = NexoraTextPrimary,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )
            VisitSpecialtyClassLine(
                specialty = model.specialty,
                classCategory = model.classCategory,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(NexoraDimens.buttonCompactHeight + 2.dp),
            horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NexoraButton(
                text = stringResource(VisitPrimaryAction.Continue.labelRes),
                onClick = onPrimaryAction,
                modifier = Modifier.weight(1f),
                fillMaxWidth = false,
                compact = true,
            )
            if (onCancel != null) {
                NexoraButton(
                    text = stringResource(R.string.action_cancel),
                    onClick = onCancel,
                    modifier = Modifier.weight(0.4f),
                    variant = NexoraButtonVariant.Muted,
                    fillMaxWidth = false,
                    compact = true,
                    leadingIcon = {
                        NexoraIcon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = null,
                            modifier = Modifier.size(11.dp),
                            tint = NexoraTextSecondary,
                        )
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun UnCompletedVisitCardPreview() {
    CRM_NexoraTheme {
        UnCompletedVisitCard(
            model = VisitSamples.planned().toUi(),
            onPrimaryAction = {},
            onCancel = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
