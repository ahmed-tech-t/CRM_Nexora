package com.example.crm_nexora.ui.screens.datacenter.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.chrome.NexoraCircularProgressIndicator
import com.example.crm_nexora.ui.components.primitives.NexoraCard
import com.example.crm_nexora.ui.components.primitives.NexoraIconButton
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncItemUi
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncStatus
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

@Composable
fun DataCenterSyncCard(
    item: DataCenterSyncItemUi,
    onSync: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NexoraCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                NexoraText(
                    text = stringResource(item.titleRes),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    ),
                    color = NexoraTextPrimary,
                )
                NexoraText(
                    text = stringResource(item.subtitleRes),
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = NexoraTextSecondary,
                )
                NexoraText(
                    text = item.lastUpdatedLabel,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                    color = NexoraTextFaint,
                )
            }

            when (item.status) {
                DataCenterSyncStatus.Idle -> NexoraIconButton(
                    painter = painterResource(
                        if (item.id == DataCenterSyncItemUi.ID_FULL) {
                            R.drawable.ic_colored_download
                        } else {
                            R.drawable.ic_download
                        },
                    ),
                    contentDescription = stringResource(R.string.cd_sync),
                    onClick = onSync,
                    shape = RoundedCornerShape(10.dp),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(38.dp),
                )

                DataCenterSyncStatus.Syncing -> NexoraCircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun DataCenterSyncCardPreview() {
    CRM_NexoraTheme {
        DataCenterSyncCard(
            item = DataCenterSyncItemUi.items().first(),
            onSync = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun DataCenterSyncCardSyncingPreview() {
    CRM_NexoraTheme {
        DataCenterSyncCard(
            item = DataCenterSyncItemUi.items().first().copy(status = DataCenterSyncStatus.Syncing),
            onSync = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
