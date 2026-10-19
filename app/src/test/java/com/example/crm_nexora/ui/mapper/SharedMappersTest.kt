package com.example.crm_nexora.ui.mapper

import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.AttendanceStatus
import com.example.crm_nexora.domain.models.AttachmentPill
import com.example.crm_nexora.domain.models.ConnectionState
import com.example.crm_nexora.domain.models.ConnectionStatus
import com.example.crm_nexora.domain.models.QuantityItem
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.models.QuantityLine
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.domain.models.StatMetric
import com.example.crm_nexora.domain.models.SyncStatus
import com.example.crm_nexora.domain.models.Visit
import com.example.crm_nexora.domain.models.VisitMode
import com.example.crm_nexora.domain.models.VisitType
import com.example.crm_nexora.ui.preview.HomeSamples
import com.example.crm_nexora.ui.preview.VisitSamples
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

class SharedMappersTest {

    @Test
    fun visit_nullFields_mapToEmptyDefaults() {
        val ui = Visit().toUi()

        assertEquals("", ui.id)
        assertEquals("", ui.backendId)
        assertEquals(LocalDate.MIN, ui.date)
        assertEquals(LocalTime.MIN, ui.time)
        assertEquals("", ui.city)
        assertEquals("", ui.placeName)
        assertEquals("", ui.account)
        assertEquals(Shift.Am, ui.shift)
        assertEquals(VisitType.Planned, ui.type)
        assertFalse(ui.isDoubleVisit)
        assertEquals("", ui.supervisorName)
        assertTrue(ui.gifts.isEmpty())
        assertTrue(ui.products.isEmpty())
        assertEquals(0, ui.giftsQty)
        assertEquals(0, ui.productsQty)
        assertEquals("", ui.voiceNote)
        assertEquals(SyncStatus.NotSynced, ui.syncStatus)
        assertEquals("", ui.durationLabel)
    }

    @Test
    fun planned_mapsCoreFieldsAndDuration() {
        val ui = VisitSamples.planned(
            placeName = "عيادة الباطنة",
            account = "د. محمود",
            specialty = "باطنة",
            classCategory = "A",
            durationLabel = "04:35 د",
        ).toUi()

        assertEquals("v1", ui.id)
        assertEquals("عيادة الباطنة", ui.placeName)
        assertEquals("د. محمود", ui.account)
        assertEquals("باطنة", ui.specialty)
        assertEquals("A", ui.classCategory)
        assertEquals(VisitType.Planned, ui.type)
        assertEquals("04:35 د", ui.durationLabel)
    }

    @Test
    fun doubleMode_setsIsDoubleVisit_andKeepsSupervisor() {
        val ui = Visit(
            type = VisitType.Planning,
            visitMode = VisitMode.Double,
            supervisorName = "د. وائل",
        ).toUi()

        assertTrue(ui.isDoubleVisit)
        assertEquals("د. وائل", ui.supervisorName)
        assertEquals(VisitType.Planning, ui.type)
    }

    @Test
    fun doubleMode_blankSupervisor_mapsToEmptyString() {
        val ui = Visit(
            type = VisitType.Planning,
            visitMode = VisitMode.Double,
            supervisorName = null,
        ).toUi()

        assertTrue(ui.isDoubleVisit)
        assertEquals("", ui.supervisorName)
    }

    @Test
    fun giftsQty_fallsBackToListSum() {
        val ui = Visit(
            gifts = listOf(
                QuantityLine("1", "a", 2),
                QuantityLine("2", "b", 3),
            ),
            giftsQty = null,
        ).toUi()

        assertEquals(2, ui.gifts.size)
        assertEquals(5, ui.giftsQty)
    }

    @Test
    fun giftsQty_explicitWinsOverListSum() {
        val ui = Visit(
            gifts = listOf(QuantityLine("1", "a", 2)),
            giftsQty = 9,
        ).toUi()

        assertEquals(9, ui.giftsQty)
    }

    @Test
    fun unplanned_typePreserved() {
        val ui = VisitSamples.unplanned(placeName = "د. هاني").toUi()
        assertEquals(VisitType.Unplanned, ui.type)
        assertEquals("د. هاني", ui.placeName)
    }

    @Test
    fun quantityItem_negativeQuantity_coercedToZero() {
        assertEquals(0, QuantityItem("g", "هدية", -3, QuantityKind.Gift).toUi().quantity)
        assertEquals(
            "0",
            QuantityItem("m", "عينة", -1, QuantityKind.Product).toUi().quantityLabel,
        )
    }

    @Test
    fun quantityItem_mapsRemainingAndKind() {
        val ui = QuantityItem(
            id = "g",
            name = "هدية",
            quantity = 2,
            kind = QuantityKind.Gift,
            remainingLabel = "متبقي: 4",
        ).toUi()
        assertEquals("متبقي: 4", ui.remainingLabel)
        assertEquals(QuantityKind.Gift, ui.kind)
    }

    @Test
    fun connectionStatus_offlineWithPendingBadge() {
        val ui = ConnectionStatus(ConnectionState.Offline, pendingSyncCount = 2).toUi()
        assertFalse(ui.isOnline)
        assertEquals(R.string.connection_offline, ui.labelRes)
        assertEquals(2, ui.pendingCount)
    }

    @Test
    fun connectionStatus_onlineWithoutBadge() {
        val ui = ConnectionStatus(ConnectionState.Online, pendingSyncCount = 0).toUi()
        assertTrue(ui.isOnline)
        assertEquals(R.string.connection_online, ui.labelRes)
        assertNull(ui.pendingCount)
    }

    @Test
    fun attachmentPill_requiredMark() {
        val required = AttachmentPill("a", "يافطة", true).toUi()
        assertEquals(R.string.attachment_required, required.requiredMarkRes)
        assertNull(AttachmentPill("a", "يافطة", false).toUi().requiredMarkRes)

        val blank = AttachmentPill("a", "", false).toUi()
        assertTrue(blank.labelBlank)
        assertEquals(R.string.attachment_fallback, blank.fallbackLabelRes)
    }

    @Test
    fun statMetric_blankValue_defaultsToZero() {
        val ui = StatMetric("s", "", "", " ").toUi()
        assertTrue(ui.titleBlank)
        assertEquals("0", ui.value)
        assertNull(ui.hint)
    }

    @Test
    fun listMappers_preserveSize() {
        val visits = listOf(
            VisitSamples.planned(id = "1"),
            VisitSamples.unplanned(id = "2"),
        )
        assertEquals(2, visits.toVisitUi().size)
    }

    @Test
    fun visitType_carriesTitleRes() {
        assertEquals(R.string.planning, VisitType.Planning.titleRes)
        assertEquals(R.string.planned, VisitType.Planned.titleRes)
        assertEquals(R.string.unplanned, VisitType.Unplanned.titleRes)
        assertEquals(R.string.visit_type_single, VisitMode.Single.labelRes)
    }
}
