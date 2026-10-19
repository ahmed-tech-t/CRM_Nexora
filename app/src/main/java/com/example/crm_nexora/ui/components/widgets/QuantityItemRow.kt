package com.example.crm_nexora.ui.components.widgets

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.models.QuantityItemUiModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

/** Child order is RTL start→end (first = right on screen). */
@Composable
fun QuantityItemRow(
    model: QuantityItemUiModel,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(NexoraDimens.radiusMd)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(NexoraDimens.borderThin, NexoraBorder.copy(alpha = 0.7f), shape)
            .background(NexoraSurfaceMuted, shape)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
        ) {
            NexoraText(
                text = model.name,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                ),
                color = NexoraTextPrimary,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )
            model.remainingLabel?.let {
                NexoraText(
                    text = it,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                    ),
                    color = NexoraTextMuted,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        QuantityStepper(
            quantityLabel = model.quantityLabel,
            onIncrement = onIncrement,
            onDecrement = onDecrement,
        )
    }
}

@Composable
private fun QuantityStepper(
    quantityLabel: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    val shape = RoundedCornerShape(NexoraDimens.radiusSm)
    Row(
        modifier = Modifier
            .border(NexoraDimens.borderThin, NexoraBorder, shape)
            .background(NexoraSurface, shape)
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        StepperButton(label = "−", dark = false, onClick = onDecrement)
        NexoraText(
            text = quantityLabel,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
            ),
            color = NexoraTextPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 6.dp),
        )
        StepperButton(label = "+", dark = true, onClick = onIncrement)
    }
}

@Composable
private fun StepperButton(
    label: String,
    dark: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(NexoraDimens.stepperBtnSize)
            .background(
                if (dark) NexoraNavy else NexoraSurfaceChip,
                RoundedCornerShape(4.dp),
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, fontSize = 12.sp),
            color = if (dark) androidx.compose.ui.graphics.Color.White else NexoraTextSecondary,
            textAlign = TextAlign.Start,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun QuantityItemRowPreview() {
    CRM_NexoraTheme {
        QuantityItemRow(
            model = QuantityItemUiModel(
                id = "1",
                name = "مج حراري + قلم معدني بشعار الشركة",
                quantityLabel = "1",
                quantity = 1,
                kind = QuantityKind.Gift,
                remainingLabel = "المتبقي بعهدتك بعد الصرف: 4 قطع",
            ),
            onIncrement = {},
            onDecrement = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
