package com.example.crm_nexora.ui.screens.unplannedvisit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.ui.components.primitives.NexoraOutlinedSurface
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.widgets.GiftProductQuantityRow
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitState
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorderFocus
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraWarningBorder
import com.example.crm_nexora.ui.theme.NexoraWarningDeep
import com.example.crm_nexora.ui.theme.NexoraWarningSurface

@Composable
fun GiftsProductsStepContent(
    state: UnplannedVisitState,
    onEvent: (UnplannedVisitEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSection),
    ) {
        SectionBlock(
            title = stringResource(R.string.unplanned_section_gifts),
            addLabel = stringResource(R.string.unplanned_add_gift),
            addContainer = NexoraWarningSurface,
            addBorder = NexoraWarningBorder,
            addContent = NexoraWarningDeep,
            onAdd = { onEvent(UnplannedVisitEvents.OpenAddItem(QuantityKind.Gift)) },
        ) {
            state.giftItems.forEach { item ->
                GiftProductQuantityRow(
                    model = item,
                    onIncrement = { onEvent(UnplannedVisitEvents.IncrementGift(item.id)) },
                    onDecrement = { onEvent(UnplannedVisitEvents.DecrementGift(item.id)) },
                )
            }
        }

        SectionBlock(
            title = stringResource(R.string.unplanned_section_products),
            addLabel = stringResource(R.string.unplanned_add_product),
            addContainer = NexoraSurfaceFocus,
            addBorder = NexoraBorderFocus,
            addContent = NexoraPrimary,
            onAdd = { onEvent(UnplannedVisitEvents.OpenAddItem(QuantityKind.Product)) },
        ) {
            state.productItems.forEach { item ->
                GiftProductQuantityRow(
                    model = item,
                    onIncrement = { onEvent(UnplannedVisitEvents.IncrementProduct(item.id)) },
                    onDecrement = { onEvent(UnplannedVisitEvents.DecrementProduct(item.id)) },
                )
            }
        }
    }
}

@Composable
private fun SectionBlock(
    title: String,
    addLabel: String,
    addContainer: Color,
    addBorder: Color,
    addContent: Color,
    onAdd: () -> Unit,
    content: @Composable () -> Unit,
) {
    NexoraOutlinedSurface(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NexoraText(
                    text = title,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    ),
                    color = NexoraTextPrimary,
                )
                NexoraText(
                    text = "+ $addLabel",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                    ),
                    color = addContent,
                    modifier = Modifier
                        .background(addContainer, RoundedCornerShape(NexoraDimens.radiusSm))
                        .border(NexoraDimens.borderThin, addBorder, RoundedCornerShape(NexoraDimens.radiusSm))
                        .clickable(onClick = onAdd)
                        .padding(horizontal = NexoraDimens.space10, vertical = NexoraDimens.spaceXxs),
                )
            }
            HorizontalDivider(color = NexoraSurfaceChip, thickness = NexoraDimens.borderThin)
            content()
        }
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 700)
@Composable
private fun GiftsProductsStepContentPreview() {
    CRM_NexoraTheme {
        GiftsProductsStepContent(
            state = UnplannedVisitViewModel.initialState(),
            onEvent = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
