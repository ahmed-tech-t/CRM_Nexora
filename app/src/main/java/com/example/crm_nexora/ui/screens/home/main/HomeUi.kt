package com.example.crm_nexora.ui.screens.home.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.chrome.nav_drawer.NavDrawerSheet
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.widgets.visit.UnplannedVisitCard
import com.example.crm_nexora.ui.screens.home.components.SyncComponent
import com.example.crm_nexora.ui.screens.home.components.UnCompletedVisitCard
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun HomeUi(
    state: HomeState,
    onEvent: (HomeEvents) -> Unit,
    modifier: Modifier = Modifier,

) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(NexoraSurfaceMuted),
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(NexoraDimens.spaceLg),
                verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
            ) {
                item(key = "sync") {
                    SyncComponent(
                        model = state.connection,
                        onSync = { onEvent(HomeEvents.OnSyncClick) },
                    )
                }

                state.activeVisit?.let { visit ->
                    item(key = "active-${visit.id}") {
                        UnCompletedVisitCard(
                            model = visit,
                            onPrimaryAction = { onEvent(HomeEvents.OnActiveContinue) },
                            onCancel = { onEvent(HomeEvents.OnActiveCancel) },
                        )
                    }
                }

                item(key = "today-header") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        NexoraText(
                            text = stringResource(R.string.home_today_visits),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                            ),
                            color = NexoraTextPrimary,
                        )
                        NexoraText(
                            text = stringResource(R.string.home_more),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            ),
                            color = NexoraPrimary,
                            modifier = Modifier.clickable {
                                onEvent(HomeEvents.OnTodayMoreClick)
                            },
                        )
                    }
                }

                items(
                    items = state.todayVisits.take(minOf(state.todayVisits.size, 3)),
                    key = { it.id },
                ) { visit ->
                    UnplannedVisitCard(
                        model = visit,
                        onAction = { onEvent(HomeEvents.OnStartVisit(visit.id)) },
                    )
                }
            }
        }
}

@Preview(showBackground = true, locale = "ar", heightDp = 900)
@Composable
private fun HomeUiPreview() {
    CRM_NexoraTheme {
        HomeUi(
            state = HomeViewModel.initialState(),
            onEvent = {},
        )
    }
}
