package com.example.crm_nexora.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.AttachmentPill
import com.example.crm_nexora.domain.models.ConnectionState
import com.example.crm_nexora.domain.models.ConnectionStatus
import com.example.crm_nexora.domain.models.StatMetric
import com.example.crm_nexora.domain.models.VisitMode
import com.example.crm_nexora.ui.components.chrome.AppHeader
import com.example.crm_nexora.ui.components.chrome.BottomNavBar
import com.example.crm_nexora.ui.components.chrome.ProgressSteps
import com.example.crm_nexora.ui.components.chrome.defaultBottomNavItems
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraLabel
import com.example.crm_nexora.ui.components.primitives.NexoraSectionTitle
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.primitives.NexoraTextField
import com.example.crm_nexora.ui.components.widgets.AttachmentPillChip

import com.example.crm_nexora.ui.components.widgets.QuantityItemRow
import com.example.crm_nexora.ui.components.widgets.StatMetricTile
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.preview.VisitSamples
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens

@Composable
fun ComponentCatalogScreen() {
    var companyCode by remember { mutableStateOf("9999") }
    var selectedNav by remember { mutableStateOf("home") }
    var planning by remember { mutableStateOf(VisitSamples.planning()) }
    var giftQty by remember { mutableStateOf(2) }
    var sampleQty by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            AppHeader(title = "Nexora Shared Components", subtitle = "Catalog smoke")
        },
        bottomBar = {
            BottomNavBar(
                items = defaultBottomNavItems(),
                selectedId = selectedNav,
                onItemSelected = { selectedNav = it },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(NexoraDimens.spaceLg)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            NexoraText("Primitives", style = MaterialTheme.typography.titleMedium)
            NexoraLabel(text = "كود الشركة")
            NexoraSectionTitle(title = "قسم تجريبي", subtitle = "وصف مختصر")
            NexoraTextField(
                value = companyCode,
                onValueChange = { companyCode = it },
                label = "كود الشركة",
                isFocusedStyle = true,
            )
            NexoraButton(text = "Primary", onClick = {})
            NexoraButton(text = "Navy", onClick = {}, variant = NexoraButtonVariant.Navy)
            NexoraButton(text = "Secondary", onClick = {}, variant = NexoraButtonVariant.Secondary)

            NexoraText("Chrome", style = MaterialTheme.typography.titleMedium)
            ProgressSteps(
                steps = listOf(
                    stringResource(R.string.step_entity_data),
                    stringResource(R.string.step_gifts_products),
                    stringResource(R.string.step_result_finish),
                ),
                currentIndex = 1,
            )

            NexoraText("Widgets", style = MaterialTheme.typography.titleMedium)


            QuantityItemRow(
                model = VisitSamples.giftItem(quantity = giftQty).toUi(),
                onIncrement = { giftQty++ },
                onDecrement = { if (giftQty > 0) giftQty-- },
            )
            QuantityItemRow(
                model = VisitSamples.productItem(quantity = sampleQty).toUi(),
                onIncrement = { sampleQty++ },
                onDecrement = { if (sampleQty > 0) sampleQty-- },
            )
            StatMetricTile(
                model = StatMetric("s1", "زيارات اليوم", "8", "من أصل 10").toUi(),
            )
            AttachmentPillChip(
                model = AttachmentPill("a1", "تصوير يافطة العيادة", true).toUi(),
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 1200)
@Composable
private fun ComponentCatalogPreview() {
    CRM_NexoraTheme {
        ComponentCatalogScreen()
    }
}
