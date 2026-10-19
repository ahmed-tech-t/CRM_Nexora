package com.example.crm_nexora.ui.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraCard
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.mapper.resolvedLabel
import com.example.crm_nexora.ui.mapper.resolvedTitle
import com.example.crm_nexora.ui.models.AttachmentPillUiModel
import com.example.crm_nexora.ui.models.StatMetricUiModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDanger
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun StatMetricTile(
    model: StatMetricUiModel,
    modifier: Modifier = Modifier,
) {
    NexoraCard(modifier = modifier.height(71.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(71.dp - NexoraDimens.spaceMd * 2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            NexoraText(
                text = model.resolvedTitle(),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                ),
                color = NexoraTextFaint,
                textAlign = TextAlign.Center,
            )
            NexoraText(
                text = model.value,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                ),
                color = NexoraTextPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun AttachmentPillChip(
    model: AttachmentPillUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(NexoraSurfaceFocus, RoundedCornerShape(NexoraDimens.radiusXl))
            .padding(horizontal = NexoraDimens.spaceMd, vertical = NexoraDimens.spaceSm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
    ) {
        NexoraText(
            text = model.resolvedLabel(),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = NexoraTextPrimary,
        )
        model.requiredMarkRes?.let { res ->
            NexoraText(
                text = stringResource(res),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, fontSize = 9.sp),
                color = NexoraDanger,
                modifier = Modifier
                    .background(NexoraDanger.copy(alpha = 0.1f), RoundedCornerShape(NexoraDimens.radiusXl))
                    .padding(horizontal = 8.dp, vertical = 2.dp),
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun StatMetricTilePreview() {
    CRM_NexoraTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatMetricTile(
                model = StatMetricUiModel("1", "زيارات اليوم", false, "26", null),
                modifier = Modifier.weight(1f),
            )
            StatMetricTile(
                model = StatMetricUiModel("2", "الزيارات المنتهية", false, "94", null),
                modifier = Modifier.weight(1f),
            )
        }
    }
}
