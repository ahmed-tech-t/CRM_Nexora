package com.example.crm_nexora.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraCard
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.widgets.StatusBadge
import com.example.crm_nexora.ui.models.ConnectionStatusUiModel
import com.example.crm_nexora.ui.models.StatusTone
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun SyncComponent(
    model: ConnectionStatusUiModel,
    modifier: Modifier = Modifier,
    onSync: (() -> Unit)? = null,
) {
    NexoraCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    model.pendingCount?.let { count ->
                        StatusBadge(
                            text = stringResource(R.string.pending_sync_format, count),
                            tone = StatusTone.Neutral,
                        )
                    }
                    NexoraText(
                        text = stringResource(model.titleRes),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        ),
                        color = NexoraTextPrimary,
                        textAlign = TextAlign.Start,
                    )
                }
                NexoraText(
                    text = stringResource(model.subtitleRes),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                    ),
                    color = NexoraTextFaint,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Button(
                onClick = { onSync?.invoke() },
                enabled = onSync != null,
                shape = RoundedCornerShape(NexoraDimens.radiusMd),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NexoraNavy,
                    contentColor = Color.White,
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp),
            ) {
                NexoraIcon(
                    painter = painterResource(R.drawable.ic_sync),
                    contentDescription = stringResource(R.string.cd_sync),
                    modifier = Modifier.size(10.dp),
                    tint = Color.White,
                )
                Spacer(Modifier.width(6.dp))
                NexoraText(
                    text = stringResource(R.string.action_sync),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                    ),
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun SyncComponentPreview() {
    CRM_NexoraTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SyncComponent(
                model = ConnectionStatusUiModel(
                    labelRes = R.string.connection_offline,
                    isOnline = false,
                    pendingCount = 3,
                ),
                onSync = {},
            )
        }
    }
}
