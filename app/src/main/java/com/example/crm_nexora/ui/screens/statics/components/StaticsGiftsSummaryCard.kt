package com.example.crm_nexora.ui.screens.statics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraCard
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.screens.statics.models.StaticsGiftsFakes
import com.example.crm_nexora.ui.screens.statics.models.StaticsGiftsItemUi
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun StaticsGiftsSummaryCard(
    items: List<StaticsGiftsItemUi>,
    modifier: Modifier = Modifier,
) {
    NexoraCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            items.forEachIndexed { index, item ->
                if (index > 0) {
                    HorizontalDivider(color = NexoraBorder.copy(alpha = 0.4f))
                }
                StaticsGiftsSummaryItemRow(item = item)
            }
        }
    }
}

@Composable
private fun StaticsGiftsSummaryItemRow(
    item: StaticsGiftsItemUi,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f, fill = false),
        ) {
            NexoraText(
                text = stringResource(item.titleRes),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                ),
                color = NexoraTextPrimary,
            )
            NexoraText(
                text = stringResource(
                    item.receivedFormatRes,
                    item.receivedQuantity,
                    item.skuCount,
                ),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                ),
                color = NexoraTextFaint,
            )
        }
        Column {
            NexoraText(
                text = stringResource(R.string.statics_custody_remaining_format, item.remaining),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                ),
                color = NexoraTextPrimary,
            )
            NexoraText(
                text = stringResource(R.string.statics_custody_dispatched_format, item.dispatched),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                ),
                color = NexoraTextFaint,
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun StaticsGiftsSummaryCardPreview() {
    CRM_NexoraTheme {
        StaticsGiftsSummaryCard(items = StaticsGiftsFakes.items)
    }
}
