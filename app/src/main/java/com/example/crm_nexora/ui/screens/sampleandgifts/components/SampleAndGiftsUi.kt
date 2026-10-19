package com.example.crm_nexora.ui.screens.sampleandgifts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsEvents
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsState
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsTab
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsViewModel
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsFakes
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemType
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraPrimaryFocus
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun SampleAndGiftsUi(
    state: SampleAndGiftsState,
    onEvent: (SampleAndGiftsEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NexoraSurface),
    ) {
        Column(modifier = Modifier.background(NexoraNavy)) {
            NexoraText(
                text = stringResource(R.string.sample_and_gifts_title),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = NexoraDimens.spaceLg,
                        end = NexoraDimens.spaceLg,
                        top = NexoraDimens.spaceSm,
                        bottom = NexoraDimens.spaceSection,
                    ),
            )
            SampleAndGiftsTabs(
                selectedTab = state.selectedTab,
                onSelectTab = { onEvent(SampleAndGiftsEvents.SelectTab(it)) },
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(NexoraSurface),
            contentPadding = PaddingValues(NexoraDimens.spaceSection),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            items(
                items = state.items,
                key = { it.id },
            ) { item ->
                SampleAndGiftsItemCard(item = item)
            }
        }
    }
}

@Composable
private fun SampleAndGiftsTabs(
    selectedTab: SampleAndGiftsTab,

    onSelectTab: (SampleAndGiftsTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val stroke = NexoraDimens.borderThin.toPx()
                drawLine(
                    color = NexoraTextPrimary,
                    start = Offset(0f, size.height - stroke / 2),
                    end = Offset(size.width, size.height - stroke / 2),
                    strokeWidth = stroke,
                )
            }
            .padding(horizontal = NexoraDimens.spaceSm),
    ) {
        // RTL start (right) = Samples — matches Figma
        SampleAndGiftsTabItem(
            label = stringResource(R.string.sample_and_gifts_tab_samples_format),
            selected = selectedTab == SampleAndGiftsTab.Samples,
            onClick = { onSelectTab(SampleAndGiftsTab.Samples) },
            modifier = Modifier.weight(1f),
        )
        SampleAndGiftsTabItem(
            label = stringResource(R.string.sample_and_gifts_tab_gifts_format),
            selected = selectedTab == SampleAndGiftsTab.Gifts,
            onClick = { onSelectTab(SampleAndGiftsTab.Gifts) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SampleAndGiftsTabItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .drawBehind {
                if (selected) {
                    val stroke = 2.dp.toPx()
                    drawLine(
                        color = NexoraPrimaryFocus,
                        start = Offset(0f, size.height - stroke / 2),
                        end = Offset(size.width, size.height - stroke / 2),
                        strokeWidth = stroke,
                    )
                }
            }
            .padding(horizontal = NexoraDimens.spaceXs, vertical = NexoraDimens.space10),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            ),
            color = if (selected) Color.White else NexoraTextMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun SampleAndGiftsUiPreview() {
    CRM_NexoraTheme {
        SampleAndGiftsUi(
            state = SampleAndGiftsViewModel.initialState().copy(
                items = SampleAndGiftsFakes.items.filter { it.type == SampleAndGiftsItemType.Sample }),

            onEvent = {},
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun GiftsUiPreview() {
    CRM_NexoraTheme {
        SampleAndGiftsUi(
            state = SampleAndGiftsViewModel.initialState()
                .copy(
                    selectedTab = SampleAndGiftsTab.Gifts,
                    items = SampleAndGiftsFakes.items.filter { it.type == SampleAndGiftsItemType.Gift }),
            onEvent = {},
        )
    }
}
