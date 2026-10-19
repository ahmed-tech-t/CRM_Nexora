package com.example.crm_nexora.ui.screens.datacenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterEvents
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterState
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

@Composable
fun DataCenterUi(
    state: DataCenterState,
    onEvent: (DataCenterEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NexoraSurfaceMuted),
    ) {
        AppHeader(
            title = stringResource(R.string.data_center_title),
            showBack = true,
            onBack = { onEvent(DataCenterEvents.OnBack) },
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(NexoraDimens.spaceLg),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            item(key = "intro") {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
                ) {
                    NexoraText(
                        text = stringResource(R.string.data_center_intro_title),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                        ),
                        color = NexoraTextPrimary,
                    )
                    NexoraText(
                        text = stringResource(R.string.data_center_intro_body),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                        color = NexoraTextSecondary,
                    )
                }
            }

            item(key = "section") {
                NexoraText(
                    text = stringResource(R.string.data_center_section_server),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    ),
                    color = NexoraTextPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = NexoraDimens.spaceSm),
                )
            }

            itemsIndexed(
                items = state.items,
                key = {index,item-> item.id}

            ) { index,item ->
                DataCenterSyncCard(
                    item = item,
                    onSync = { onEvent(DataCenterEvents.OnSync(item.id)) },
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar", heightDp = 800)
@Composable
private fun DataCenterUiPreview() {
    CRM_NexoraTheme {
        DataCenterUi(
            state = DataCenterViewModel.initialState(),
            onEvent = {},
        )
    }
}
