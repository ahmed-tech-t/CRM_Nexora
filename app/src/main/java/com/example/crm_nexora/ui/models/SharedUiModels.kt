package com.example.crm_nexora.ui.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.AttendanceStatus
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.domain.models.SyncStatus
import com.example.crm_nexora.domain.models.VisitType
import java.time.LocalDate
import java.time.LocalTime

/**
 * Non-null UI visit. Domain nulls are mapped to empty defaults in [com.example.crm_nexora.ui.mapper.toUi].
 */
@Immutable
data class VisitUi(
    val id: String = "",
    val backendId: String = "",
    val date: LocalDate = LocalDate.MIN,
    val time: LocalTime = LocalTime.MIN,
    val city: String = "",
    val region: String = "",
    val address: String = "",
    val account: String = "",
    val placeName: String = "",
    val specialty: String = "",
    val shift: Shift = Shift.Am,
    val isDoubleVisit: Boolean = false,
    val type: VisitType = VisitType.Planned,
    val supervisorName: String = "",
    val classCategory: String = "",
    val gifts: List<GiftProductUiModel> = emptyList(),
    val products: List<GiftProductUiModel> = emptyList(),
    val giftsQty: Int = 0,
    val productsQty: Int = 0,
    val voiceNote: String = "",
    val syncStatus: SyncStatus = SyncStatus.NotSynced,
    val durationLabel: String = "",
)

/** Gift or product/sample line for visit custody UI. */
@Immutable
data class GiftProductUiModel(
    val id: String,
    val name: String,
    val quantityLabel: String,
    val quantity: Int,
    val kind: QuantityKind,
    val remainingLabel: String? = null,
)

@Immutable
data class StatMetricUiModel(
    val id: String,
    val title: String,
    val titleBlank: Boolean,
    val value: String,
    val hint: String?,
)

@Immutable
data class ConnectionStatusUiModel(
    @StringRes val labelRes: Int,
    val isOnline: Boolean,
    val pendingCount: Int?,
    @StringRes val titleRes: Int = R.string.offline_sync_title,
    @StringRes val subtitleRes: Int = R.string.offline_sync_subtitle,
)

@Immutable
data class AttachmentPillUiModel(
    val id: String,
    val label: String,
    val labelBlank: Boolean,
    @StringRes val fallbackLabelRes: Int = R.string.attachment_fallback,
    @StringRes val requiredMarkRes: Int?,
)

enum class StatusTone {
    Neutral,
    Success,
    Warning,
    Danger,
}

@Immutable
data class UserUi(
    val id: String,
    val displayName: String,
    val roleTitle: String,
    val specialty: String = "",
    val employeeCode: String = "",
    val deviceId: String = "",
    val initials: String = "",
)

@Immutable
data class AttendanceUi(
    val status: AttendanceStatus,
    val checkInTimeLabel: String,
    /** Display-only place name (domain stores lat/lng only). */
    val checkInLocationLabel: String = "",
    val checkInLat: Double?,
    val checkInLng: Double?,
    val checkOutTimeLabel: String,
    val checkOutLocationLabel: String = "",
    val checkOutLat: Double?,
    val checkOutLng: Double?,
) {
    val isCheckedIn: Boolean get() = status == AttendanceStatus.CheckedIn
}
