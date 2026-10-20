package com.example.crm_nexora.ui.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

/**
 * Shared − / qty / + control.
 * [large] = sheet size (28) + Primary plus; otherwise list row (20) + Navy plus.
 */
@Composable
fun QuantityStepper(
    quantityLabel: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(NexoraDimens.radiusMd)
    val gap =  NexoraDimens.spaceMd
    val plusColor = NexoraNavy
    Row(
        modifier = modifier
            .border(NexoraDimens.borderThin, NexoraBorder, shape)
            .background(NexoraSurface, shape)
            .padding(NexoraDimens.spaceXxs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap),
    ) {
        StepperButton(
            label = "−",
            background = NexoraSurfaceChip,
            contentColor = NexoraTextMuted,
            size =  NexoraDimens.sheetStepperBtnSize,
            onClick = onDecrement,
        )
        NexoraText(
            text = quantityLabel,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = NexoraTextPrimary,
            textAlign = TextAlign.Center,
        )
        StepperButton(
            label = "+",
            background = plusColor,
            contentColor = Color.White,
            size =  NexoraDimens.sheetStepperBtnSize,
            onClick = onIncrement,
        )
    }
}

@Composable
fun QuantityStepper(
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuantityStepper(
        quantityLabel = quantity.toString(),
        onIncrement = onIncrement,
        onDecrement = onDecrement,
        modifier = modifier,
    )
}

@Composable
private fun StepperButton(
    label: String,
    background: Color,
    contentColor: Color,
    size: Dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(background, RoundedCornerShape(NexoraDimens.radiusSm))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = contentColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun QuantityStepperCompactPreview() {
    CRM_NexoraTheme {
        QuantityStepper(
            quantityLabel = "2",
            onIncrement = {},
            onDecrement = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
