package com.example.crm_nexora.ui.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.models.GiftProductUiModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSky
import com.example.crm_nexora.ui.theme.NexoraSkyBorder
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun GiftProductQuantityRow(
    model: GiftProductUiModel,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardShape = RoundedCornerShape(NexoraDimens.radiusLg)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NexoraSurfaceMuted, cardShape)
            .border(NexoraDimens.borderThin, NexoraSkyBorder, cardShape)
            .padding(NexoraDimens.spaceLg),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXxs),
        ) {
            NexoraText(
                text = model.name,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = NexoraTextPrimary,
            )
            model.remainingLabel?.let { remaining ->
                NexoraText(
                    text = remaining,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    ),
                    color = NexoraTextMuted,
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

@Preview(showBackground = true, locale = "ar")
@Composable
private fun GiftProductQuantityRowPreview() {
    CRM_NexoraTheme {
        GiftProductQuantityRow(
            model = GiftProductUiModel(
                id = "1",
                name = "مج حراري + قلم معدني بشعار الشركة",
                quantityLabel = "1",
                quantity = 1,
                kind = QuantityKind.Gift,
                remainingLabel = "المتبقي بعهدتك بعد الصرف: 4 قطع",
            ),
            onIncrement = {},
            onDecrement = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
