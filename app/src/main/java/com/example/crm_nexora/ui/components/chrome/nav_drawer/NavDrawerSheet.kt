package com.example.crm_nexora.ui.components.chrome.nav_drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.models.UserUi
import com.example.crm_nexora.ui.preview.HomeSamples
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

private val DrawerWidth = 259.dp
private val DeviceIdBadgeBg = Color(0xFF064E3B)

/** Figma side drawer (node 33:7964) — profile header, menu rows, footer. */
@Composable
fun NavDrawerSheet(
    user: UserUi,
    appVersion: String,
    onItemClick: (NavDrawerItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(DrawerWidth)
            .fillMaxHeight()
            .background(NexoraNavy)
            .padding(top = 64.dp, bottom = 16.dp),
    ) {
        DrawerHeader(user = user)
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = NexoraDimens.spaceMd),
            color = Color.White.copy(alpha = 0.08f),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            NavDrawerItem.all.forEach { item ->
                DrawerMenuRow(
                    label = stringResource(item.labelRes),
                    onClick = { onItemClick(item) },
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            NexoraText(
                text = stringResource(R.string.drawer_copyright),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    lineHeight = 14.sp,
                ),
                color = NexoraTextFaint,
                textAlign = TextAlign.Center,
            )
            NexoraText(
                text = stringResource(R.string.drawer_app_version_format, appVersion),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                color = NexoraTextSecondary,
            )
        }
    }
}

@Composable
private fun DrawerHeader(
    user: UserUi,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(NexoraPrimary),
            contentAlignment = Alignment.Center,
        ) {
            NexoraText(
                text = user.initials.ifBlank { user.displayName.take(2) },
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                ),
                color = Color.White,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            NexoraText(
                text = user.displayName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
                color = Color.White,
            )
            NexoraText(
                text = stringResource(
                    R.string.drawer_specialty_code_format,
                    user.specialty,
                    user.employeeCode,
                ),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                color = NexoraTextSecondary,
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(DeviceIdBadgeBg)
                    .padding(horizontal = 8.dp, vertical = 2.dp),
            ) {
                NexoraText(
                    text = stringResource(R.string.drawer_device_id_format, user.deviceId),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                    ),
                    color = NexoraSuccess,
                )
            }
        }
    }
}

@Composable
private fun DrawerMenuRow(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(RoundedCornerShape(NexoraDimens.radiusSm))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
    ) {
        NexoraText(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
            ),
            color = Color.White,
        )
    }
}

@Preview(showBackground = true, locale = "ar", backgroundColor = 0xFF0F172A)
@Composable
private fun NavDrawerSheetPreview() {
    CRM_NexoraTheme {
        NavDrawerSheet(
            user = HomeSamples.user().toUi(),
            appVersion = stringResource(R.string.drawer_app_version_default),
            onItemClick = {},
        )
    }
}
