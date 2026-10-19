package com.example.crm_nexora.ui.screens.statics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.chrome.AppHeader
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.screens.statics.main.StaticsEvents
import com.example.crm_nexora.ui.screens.statics.main.StaticsState
import com.example.crm_nexora.ui.screens.statics.main.StaticsViewModel
import com.example.crm_nexora.ui.screens.statics.models.StaticsMetricUi
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextMuted

@Composable
fun StaticsUi(
    state: StaticsState,
    onEvent: (StaticsEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NexoraSurfaceMuted),
    ) {
        AppHeader(title = stringResource(R.string.statics_title))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(NexoraDimens.spaceLg),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSection),
        ) {
            item(key = "visits-section") {
                NexoraText(
                    text = stringResource(R.string.statics_section_visits),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                    ),
                    color = NexoraTextMuted,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            item(key = "visit-metrics") {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 2,
                    verticalArrangement = Arrangement.spacedBy(space = NexoraDimens.spaceMd),
                    horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
                ) {

                    StaticsMetricCard(
                        metric = StaticsMetricUi.Completed(state.completedPlan),
                        modifier = Modifier.weight(1f)
                    )
                    StaticsMetricCard(
                        metric = StaticsMetricUi.Today(state.todayVisits),
                        modifier = Modifier.weight(1f)
                    )

                    StaticsMetricCard(
                        metric = StaticsMetricUi.Unplanned(state.unplannedVisits),
                        modifier = Modifier.weight(1f)
                    )



                }
            }
            item(key = "custody-section") {
                Spacer(modifier = Modifier.height(NexoraDimens.spaceLg))
                NexoraText(
                    text = stringResource(R.string.statics_section_custody),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                    ),
                    color = NexoraTextMuted,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            item(key = "custody-card") {
                StaticsGiftsSummaryCard(items = state.custodyItems)
            }

            item(key = "accounts-card") {
                StaticsAccountDistributionCard(categories = state.accountCategories)
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar", widthDp = 374, heightDp = 800)
@Composable
private fun StaticsUiPreview() {
    CRM_NexoraTheme {
        StaticsUi(
            state = StaticsViewModel.initialState(),
            onEvent = {},
        )
    }
}
