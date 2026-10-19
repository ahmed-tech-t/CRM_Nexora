package com.example.crm_nexora.ui.components.widgets.visit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.VisitPrimaryAction
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.mapper.resolvedAccountOrSpecialtyTag
import com.example.crm_nexora.ui.mapper.resolvedDisplayName
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.models.VisitUi
import com.example.crm_nexora.ui.preview.VisitSamples
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun PlannedVisitCard(
    model: VisitUi,
    onAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .visitCardChrome(elevation = 2.dp, shadowAlpha = 0.02f)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f, fill = false),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
            ) {
                NexoraText(
                    text = model.resolvedDisplayName(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                    ),
                    color = NexoraTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                VisitTagChip(text = model.resolvedAccountOrSpecialtyTag())
            }
            NexoraText(
                text = model.address,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                color = NexoraTextMuted,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        NexoraButton(
            text = stringResource(VisitPrimaryAction.Start.labelRes),
            onClick = onAction,
            fillMaxWidth = false,
            compact = true,
            leadingIcon = {
                NexoraIcon(
                    painter = painterResource(R.drawable.ic_arrow_visit),
                    contentDescription = null,
                    modifier = Modifier.size(11.dp),
                    tint = Color.White,
                )
            },
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun PlannedVisitCardPreview() {
    CRM_NexoraTheme {
        PlannedVisitCard(
            model = VisitSamples.unplanned().toUi(),
            onAction = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
