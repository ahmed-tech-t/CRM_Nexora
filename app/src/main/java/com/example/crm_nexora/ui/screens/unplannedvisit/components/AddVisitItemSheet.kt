package com.example.crm_nexora.ui.screens.unplannedvisit.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.ui.components.chrome.AppBottomSheet
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.widgets.GiftProductQuantityRow
import com.example.crm_nexora.ui.components.widgets.NexoraSearchableDropdown
import com.example.crm_nexora.ui.components.widgets.SearchableOptionUi
import com.example.crm_nexora.ui.models.GiftProductUiModel
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitOverlay
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitState
import com.example.crm_nexora.ui.screens.unplannedvisit.models.UnplannedVisitFakes
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary
import kotlinx.coroutines.flow.distinctUntilChanged

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVisitItemSheetHost(
    state: UnplannedVisitState,
    onEvent: (UnplannedVisitEvents) -> Unit,
) {
    val overlay = state.overlay as? UnplannedVisitOverlay.AddItem ?: return
    val configuration = LocalConfiguration.current
    val maxSheetHeight = remember(configuration) { (configuration.screenHeightDp * 0.85f).dp }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val catalog = remember(overlay.kind) { UnplannedVisitFakes.catalogFor(overlay.kind) }

    AppBottomSheet(
        sheetState = sheetState,
        onDismiss = { onEvent(UnplannedVisitEvents.DismissOverlay) },
    ) {
        AddVisitItemSheetContent(
            modifier = Modifier.heightIn(max = maxSheetHeight),
            kind = overlay.kind,
            catalog = catalog,
            onConfirm = { items -> onEvent(UnplannedVisitEvents.ConfirmAddItems(items)) },
            onDismiss = { onEvent(UnplannedVisitEvents.DismissOverlay) },
        )
    }
}

@Composable
fun AddVisitItemSheetContent(
    kind: QuantityKind,
    catalog: List<GiftProductUiModel>,
    onConfirm: (List<GiftProductUiModel>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val drafts = remember { mutableStateListOf<GiftProductUiModel>() }
    val textFieldState = rememberTextFieldState()
    var query by remember { mutableStateOf("") }
    val filteredCatalog by remember(catalog, query) {
        derivedStateOf {
            if (query.isBlank()) catalog
            else catalog.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
    LaunchedEffect(textFieldState) {
        snapshotFlow { textFieldState.text.toString() }
            .distinctUntilChanged()
            .collect { query = it }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceLg),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NexoraText(
                text = stringResource(
                    when (kind) {
                        QuantityKind.Gift -> R.string.unplanned_sheet_title_gifts
                        QuantityKind.Product -> R.string.unplanned_sheet_title_products
                    },
                ),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 19.sp,
                ),
                color = NexoraTextPrimary,
                modifier = Modifier.weight(1f),
            )
            Box(
                modifier = Modifier
                    .size(NexoraDimens.buttonCompactHeight)
                    .background(NexoraSurfaceChip, CircleShape)
                    .clickable(onClick = onDismiss),
                contentAlignment = Alignment.Center,
            ) {
                NexoraIcon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = NexoraTextMuted,
                    modifier = Modifier.size(NexoraDimens.iconSizeMd),
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXs)) {
            NexoraText(
                text = stringResource(R.string.unplanned_sheet_search_label),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = NexoraTextSecondary,
            )
            NexoraSearchableDropdown(
                state = textFieldState,
                options = filteredCatalog.map { SearchableOptionUi(it.id, it.name) },
                selectedId = null,
                onSelect = { id ->
                    if (drafts.any { it.id == id }) return@NexoraSearchableDropdown
                    val item = catalog.firstOrNull { it.id == id } ?: return@NexoraSearchableDropdown
                    drafts.add(item.copy(quantity = 1, quantityLabel = "1"))
                },
                placeholder = stringResource(
                    when (kind) {
                        QuantityKind.Gift -> R.string.unplanned_sheet_search_hint_gift
                        QuantityKind.Product -> R.string.unplanned_sheet_search_hint_product
                    },
                ),
            )
        }

        if (drafts.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 240.dp),
                verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
            ) {
                items(drafts, key = { it.id }) { item ->
                    GiftProductQuantityRow(
                        model = item,
                        onIncrement = { drafts.adjustQuantity(item.id, +1) },
                        onDecrement = { drafts.adjustQuantity(item.id, -1) },
                    )
                }
            }
        }

        NexoraButton(
            text = stringResource(R.string.unplanned_sheet_save),
            onClick = { onConfirm(drafts.toList()) },
            variant = NexoraButtonVariant.Navy,
            enabled = drafts.isNotEmpty(),
        )
    }
}

private fun SnapshotStateList<GiftProductUiModel>.adjustQuantity(id: String, delta: Int) {
    val index = indexOfFirst { it.id == id }
    if (index < 0) return
    val item = this[index]
    val qty = (item.quantity + delta).coerceAtLeast(0)
    if (qty == 0) {
        removeAt(index)
    } else {
        this[index] = item.copy(quantity = qty, quantityLabel = qty.toString())
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun AddVisitItemSheetContentPreview() {
    CRM_NexoraTheme {
        AddVisitItemSheetContent(
            kind = QuantityKind.Gift,
            catalog = UnplannedVisitFakes.catalogFor(QuantityKind.Gift),
            onConfirm = {},
            onDismiss = {},
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
