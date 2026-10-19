package com.example.crm_nexora.ui.mapper

import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.Attendance
import com.example.crm_nexora.domain.models.AttachmentPill
import com.example.crm_nexora.domain.models.ConnectionState
import com.example.crm_nexora.domain.models.ConnectionStatus
import com.example.crm_nexora.domain.models.QuantityItem
import com.example.crm_nexora.domain.models.QuantityLine
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.domain.models.StatMetric
import com.example.crm_nexora.domain.models.SyncStatus
import com.example.crm_nexora.domain.models.User
import com.example.crm_nexora.domain.models.Visit
import com.example.crm_nexora.domain.models.VisitMode
import com.example.crm_nexora.domain.models.VisitType
import com.example.crm_nexora.ui.models.AttendanceUi
import com.example.crm_nexora.ui.models.AttachmentPillUiModel
import com.example.crm_nexora.ui.models.ConnectionStatusUiModel
import com.example.crm_nexora.ui.models.QuantityItemUiModel
import com.example.crm_nexora.ui.models.QuantityLineUiModel
import com.example.crm_nexora.ui.models.StatMetricUiModel
import com.example.crm_nexora.ui.models.UserUi
import com.example.crm_nexora.ui.models.VisitUi
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Visit.toUi(): VisitUi {
    val giftLines = gifts.orEmpty().map { it.toUiLine() }
    val productLines = products.orEmpty().map { it.toUiLine() }
    val resolvedGiftsQty = giftsQty ?: giftLines.sumOf { it.quantity }
    val resolvedProductsQty = productsQty ?: productLines.sumOf { it.quantity }

    return VisitUi(
        id = id.orEmpty(),
        date = date ?: LocalDate.MIN,
        time = time ?: LocalTime.MIN,
        city = city.orEmpty(),
        region = region.orEmpty(),
        address = address.orEmpty(),
        account = account.orEmpty(),
        placeName = placeName.orEmpty(),
        specialty = specialty.orEmpty(),
        shift = shift ?: Shift.Am,
        isDoubleVisit = visitMode == VisitMode.Double,
        type = type ?: VisitType.Planned,
        supervisorName = supervisorName.orEmpty(),
        classCategory = classCategory.orEmpty(),
        gifts = giftLines,
        products = productLines,
        giftsQty = resolvedGiftsQty,
        productsQty = resolvedProductsQty,
        voiceNote = voiceNote.orEmpty(),
        syncStatus = syncStatus ?: SyncStatus.NotSynced,
        durationLabel = durationLabel.orEmpty(),
    )
}

fun QuantityItem.toUi(): QuantityItemUiModel {
    val qty = quantity.coerceAtLeast(0)
    return QuantityItemUiModel(
        id = id,
        name = name,
        quantity = qty,
        quantityLabel = qty.toString(),
        kind = kind,
        remainingLabel = remainingLabel?.takeIf { it.isNotBlank() },
    )
}

fun StatMetric.toUi(): StatMetricUiModel = StatMetricUiModel(
    id = id,
    title = title,
    titleBlank = title.isBlank(),
    value = value.ifBlank { "0" },
    hint = hint?.takeIf { it.isNotBlank() },
)

fun ConnectionStatus.toUi(): ConnectionStatusUiModel {
    val online = state == ConnectionState.Online
    return ConnectionStatusUiModel(
        labelRes = state.labelRes,
        isOnline = online,
        pendingCount = pendingSyncCount.takeIf { it > 0 },
    )
}

fun AttachmentPill.toUi(): AttachmentPillUiModel = AttachmentPillUiModel(
    id = id,
    label = label,
    labelBlank = label.isBlank(),
    requiredMarkRes = if (isRequired) R.string.attachment_required else null,
)

fun List<Visit>.toVisitUi(): List<VisitUi> = map { it.toUi() }
fun List<QuantityItem>.toQuantityUi(): List<QuantityItemUiModel> = map { it.toUi() }
fun List<StatMetric>.toStatMetricUi(): List<StatMetricUiModel> = map { it.toUi() }

fun User.toUi(): UserUi = UserUi(
    id = id,
    displayName = displayName,
    roleTitle = roleTitle,
    specialty = specialty,
    employeeCode = employeeCode,
    deviceId = deviceId,
    initials = initials,
)

fun Attendance.toUi(
    zoneId: ZoneId = ZoneId.systemDefault(),
    locale: Locale = Locale("ar"),
): AttendanceUi = AttendanceUi(
    status = status,
    checkInTimeLabel = checkInAt.toAttendanceTimeLabel(zoneId, locale),
    checkInLat = checkInLat,
    checkInLng = checkInLng,
    checkOutTimeLabel = checkOutAt.toAttendanceTimeLabel(zoneId, locale),
    checkOutLat = checkOutLat,
    checkOutLng = checkOutLng,
)

private fun Instant?.toAttendanceTimeLabel(zoneId: ZoneId, locale: Locale): String {
    if (this == null) return ""
    val formatter = DateTimeFormatter.ofPattern("hh:mm a", locale)
    return atZone(zoneId).toLocalTime().format(formatter)
}

private fun QuantityLine.toUiLine(): QuantityLineUiModel {
    val qty = quantity.coerceAtLeast(0)
    return QuantityLineUiModel(
        id = id,
        name = name,
        quantity = qty,
        quantityLabel = qty.toString(),
    )
}

