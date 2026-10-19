package com.example.crm_nexora.domain.models

import androidx.annotation.StringRes
import com.example.crm_nexora.R
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime



/** Planning / Planned / Unplanned kind. */
sealed class VisitType(@StringRes val titleRes: Int) {
    data object Planning : VisitType(R.string.planning)
    data object Planned : VisitType(R.string.planned)
    data object Unplanned : VisitType(R.string.unplanned)
}

/** Single vs Double visit mode. */
sealed class VisitMode(@StringRes val labelRes: Int) {
    data object Single : VisitMode(R.string.visit_type_single)
    data object Double : VisitMode(R.string.visit_type_double)
}

sealed class ConnectionState(@StringRes val labelRes: Int) {
    data object Online : ConnectionState(R.string.connection_online)
    data object Offline : ConnectionState(R.string.connection_offline)
}

sealed class VisitPrimaryAction(@StringRes val labelRes: Int) {
    data object Start : VisitPrimaryAction(R.string.visit_action_start)
    data object Continue : VisitPrimaryAction(R.string.visit_action_continue)
}

enum class Shift {
    Am,
    Pm,
}

sealed class SyncStatus {
    data object Synced : SyncStatus()
    data object NotSynced : SyncStatus()
}

data class QuantityLine(
    val id: String,
    val name: String,
    val quantity: Int,
)

/**
 * Domain visit — all fields nullable for partial drafts / API gaps.
 * [account] is a free-form label (doctor / clinic / hospital / …).
 */
data class Visit(
    val id: String? = null,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val city: String? = null,
    val region: String? = null,
    val address: String? = null,
    val account: String? = null,
    val placeName: String? = null,
    val specialty: String? = null,
    val shift: Shift? = null,
    val type: VisitType? = null,
    val visitMode: VisitMode? = null,
    val supervisorName: String? = null,
    val classCategory: String? = null,
    val gifts: List<QuantityLine>? = null,
    val products: List<QuantityLine>? = null,
    val giftsQty: Int? = null,
    val productsQty: Int? = null,
    val voiceNote: String? = null,
    val syncStatus: SyncStatus? = null,
    val durationLabel: String? = null,
)

enum class QuantityKind {
    Gift,
    Product,
}

data class QuantityItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val kind: QuantityKind,
    val remainingLabel: String? = null,
)

data class StatMetric(
    val id: String,
    val title: String,
    val value: String,
    val hint: String? = null,
)

data class ConnectionStatus(
    val state: ConnectionState,
    val pendingSyncCount: Int = 0,
)

data class AttachmentPill(
    val id: String,
    val label: String,
    val isRequired: Boolean = false,
)

enum class AttendanceStatus {
    CheckedIn,
    CheckedOut,
}

data class User(
    val id: String,
    val displayName: String,
    val roleTitle: String,
    val specialty: String = "",
    val employeeCode: String = "",
    val deviceId: String = "",
    val initials: String = "",
)

/**
 * Manual shift attendance — check-in / check-out with lat/lng (not tied to first/last visit).
 */
data class Attendance(
    val status: AttendanceStatus,
    val checkInAt: Instant? = null,
    val checkInLat: Double? = null,
    val checkInLng: Double? = null,
    val checkOutAt: Instant? = null,
    val checkOutLat: Double? = null,
    val checkOutLng: Double? = null,
)
