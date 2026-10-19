package com.example.crm_nexora.ui.screens.sampleandgifts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsFakes
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemType
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemUi
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextSecondary
import com.example.crm_nexora.ui.theme.NexoraWarningBorder
import com.example.crm_nexora.ui.theme.NexoraWarningChip
import com.example.crm_nexora.ui.theme.NexoraWarningDeep
import com.example.crm_nexora.ui.theme.NexoraWarningSurface

@Composable
fun SampleAndGiftsItemCard(
    item: SampleAndGiftsItemUi,
    modifier: Modifier = Modifier,
) {
    NexoraCard(
        modifier = modifier,
        contentPadding = PaddingValues(NexoraDimens.spaceSection),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(NexoraDimens.space10),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                NexoraText(
                    text = item.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                    ),
                    color = NexoraNavy,
                    modifier = Modifier.fillMaxWidth(),
                )
                if (item.type == SampleAndGiftsItemType.Sample && item.isLowStock) {
                    LowStockChip()
                }
                ReceivedDispatchedRow(
                    received = item.received,
                    dispatched = item.dispatched,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            when (item.type) {
                SampleAndGiftsItemType.Sample -> SampleRemainingBox(item = item)
                SampleAndGiftsItemType.Gift -> GiftPiecesPill(remaining = item.remaining)
            }
        }
    }
}

@Composable
private fun ReceivedDispatchedRow(
    received: Int,
    dispatched: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXxs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NexoraText(
            text = stringResource(R.string.sample_and_gifts_received_label),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.5.sp,
            ),
            color = NexoraTextMuted,
        )
        NexoraText(
            text = received.toString(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Black,
                fontSize = 10.5.sp,
            ),
            color = NexoraTextMuted,
        )
        NexoraText(
            text = stringResource(R.string.sample_and_gifts_dispatched_label),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.5.sp,
            ),
            color = NexoraTextMuted,
        )
        NexoraText(
            text = dispatched.toString(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Black,
                fontSize = 10.5.sp,
            ),
            color = NexoraTextMuted,
        )
    }
}

@Composable
private fun LowStockChip(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(NexoraWarningChip, RoundedCornerShape(999.dp))
            .padding(horizontal = 9.dp, vertical = 3.dp),
    ) {
        NexoraText(
            text = stringResource(R.string.sample_and_gifts_low_stock),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                lineHeight = 13.sp,
            ),
            color = NexoraWarningDeep,
        )
    }
}

@Composable
private fun SampleRemainingBox(
    item: SampleAndGiftsItemUi,
    modifier: Modifier = Modifier,
) {
    val bg = if (item.isLowStock) NexoraWarningSurface else NexoraSurfaceMuted
    val border = if (item.isLowStock) NexoraWarningBorder else NexoraBorder
    val numberColor = if (item.isLowStock) NexoraWarningDeep else NexoraNavy
    Box(
        modifier = modifier
            .widthIn(min = 69.dp)
            .background(bg, RoundedCornerShape(NexoraDimens.radiusMd))
            .border(NexoraDimens.borderThin, border, RoundedCornerShape(NexoraDimens.radiusMd))
            .padding(horizontal = NexoraDimens.spaceMd, vertical = 5.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NexoraText(
                text = item.remaining.toString(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    lineHeight = 16.5.sp,
                ),
                color = numberColor,
                textAlign = TextAlign.Center,
            )
            NexoraText(
                text = item.remainingUnit,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.5.sp,
                    lineHeight = 12.sp,
                ),
                color = NexoraTextMuted,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun GiftPiecesPill(
    remaining: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(NexoraSurfaceChip, RoundedCornerShape(999.dp))
            .padding(horizontal = 9.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = stringResource(R.string.sample_and_gifts_pieces_format, remaining),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
            ),
            color = NexoraTextSecondary,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun SampleAndGiftsItemCardSamplePreview() {
    CRM_NexoraTheme {
        SampleAndGiftsItemCard(
            item = SampleAndGiftsFakes.items.first {
                it.type == SampleAndGiftsItemType.Sample && it.isLowStock
            },
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun SampleAndGiftsItemCardGiftPreview() {
    CRM_NexoraTheme {
        SampleAndGiftsItemCard(
            item = SampleAndGiftsFakes.items.first {
                it.type == SampleAndGiftsItemType.Gift
            },
        )
    }
}
