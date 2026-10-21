package com.example.crm_nexora.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.models.AttendanceUi
import com.example.crm_nexora.ui.models.ConnectionStatusUiModel
import com.example.crm_nexora.ui.models.UserUi
import com.example.crm_nexora.ui.preview.HomeSamples
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraAttendanceCardBg
import com.example.crm_nexora.ui.theme.NexoraAttendanceCardBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraEndShiftBg
import com.example.crm_nexora.ui.theme.NexoraEndShiftBorder
import com.example.crm_nexora.ui.theme.NexoraEndShiftText
import com.example.crm_nexora.ui.theme.NexoraLocationLabel
import com.example.crm_nexora.ui.theme.NexoraMenuBtnBg
import com.example.crm_nexora.ui.theme.NexoraMenuBtnBorder
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraOnlineBadgeBg
import com.example.crm_nexora.ui.theme.NexoraOnlineBadgeBorder
import com.example.crm_nexora.ui.theme.NexoraOnlineBadgeText
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

/** Figma home header (node 33:8060) — profile row + attendance card. */
@Composable
fun HomeAttendanceHeader(
    user: UserUi,
    attendance: AttendanceUi,
    connection: ConnectionStatusUiModel,
    onStartShift: () -> Unit,
    onEndShift: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Screen: name+menu on RIGHT, online chip on LEFT (Figma 33:8060)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
            ) {
                Column(horizontalAlignment = Alignment.Start) {
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
                        text = user.roleTitle,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 9.sp,
                            lineHeight = 16.5.sp,
                        ),
                        color = NexoraTextFaint,
                    )
                }
            }
            ConnectionStatusChip(
                label = stringResource(connection.labelRes),
                isOnline = connection.isOnline,
            )
        }

        // Screen: attendance text on RIGHT, shift button on LEFT (Figma 33:8087)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(NexoraAttendanceCardBg, RoundedCornerShape(NexoraDimens.radiusLg))
                .border(1.dp, NexoraAttendanceCardBorder, RoundedCornerShape(NexoraDimens.radiusLg))
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                if (attendance.isCheckedIn) {
                    NexoraText(
                        text = stringResource(R.string.attendance_recorded),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                        ),
                        color = NexoraTextFaint,
                    )
                    // RTL start=right → time • location
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        NexoraText(
                            text = attendance.checkInTimeLabel,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                            ),
                            color = Color.White,
                        )
                        if (attendance.checkInLocationLabel.isNotBlank()) {
                            NexoraText(
                                text = "•",
                                style = MaterialTheme.typography.bodyMedium,
                                color = NexoraSuccess,
                            )
                            NexoraText(
                                text = attendance.checkInLocationLabel,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                ),
                                color = NexoraLocationLabel,
                            )
                        }
                    }
                } else {
                    NexoraText(
                        text = stringResource(R.string.attendance_not_started),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = NexoraTextFaint,
                    )
                }
            }
                NexoraButton(
                    text =    if (attendance.isCheckedIn) stringResource(R.string.attendance_start_shift) else  stringResource(R.string.attendance_start_shift),
                    onClick = onStartShift,
                    fillMaxWidth = false,
                    border = BorderStroke(width = 1.dp, color = Color(0xB3383131)),
                    variant = NexoraButtonVariant.Primary,
                    compact = true,
                    leadingIcon = {
                        NexoraIcon(
                            painter = painterResource(R.drawable.ic_power),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.White,
                        )
                    },
                )

        }
    }
}

@Composable
private fun ConnectionStatusChip(
    label: String,
    isOnline: Boolean,
    modifier: Modifier = Modifier,
) {
    val bg = if (isOnline) NexoraOnlineBadgeBg else Color.White.copy(alpha = 0.06f)
    val border = if (isOnline) NexoraOnlineBadgeBorder else Color.White.copy(alpha = 0.12f)
    val textColor = if (isOnline) NexoraOnlineBadgeText else NexoraTextFaint
    val dotColor = if (isOnline) NexoraSuccess else NexoraTextFaint

    Row(
        modifier = modifier
            .height(32.dp)
            .background(bg, RoundedCornerShape(999.dp))
            .border(1.dp, border, RoundedCornerShape(999.dp))
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(dotColor, CircleShape),
        )
        NexoraText(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            ),
            color = textColor,
        )

    }
}


@Preview(showBackground = true, locale = "ar", backgroundColor = 0xFF095D7E)
@Composable
private fun HomeAttendanceHeaderCheckedInPreview() {
    CRM_NexoraTheme {
        HomeAttendanceHeader(
            user = HomeSamples.user().toUi(),
            attendance = HomeSamples.attendanceCheckedIn().toUi().copy(
                checkInLocationLabel = "ميدان شرف",
            ),
            connection = HomeSamples.connectionOnline().toUi(),
            onStartShift = {},
            onEndShift = {},
        )
    }
}
