package com.example.crm_nexora.ui.preview

import com.example.crm_nexora.domain.models.Attendance
import com.example.crm_nexora.domain.models.AttendanceStatus
import com.example.crm_nexora.domain.models.ConnectionState
import com.example.crm_nexora.domain.models.ConnectionStatus
import com.example.crm_nexora.domain.models.User
import com.example.crm_nexora.domain.models.Visit
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

/** Shared Home sample data for Previews, ViewModel fake state, and tests. */
object HomeSamples {

    fun user(
        id: String = "u1",
        displayName: String = "أحمد محمد",
        roleTitle: String = "ميديكال ريب",
        specialty: String = "التخصص",
        employeeCode: String = "104",
        deviceId: String = "99201",
        initials: String = "أم",
    ): User = User(
        id = id,
        displayName = displayName,
        roleTitle = roleTitle,
        specialty = specialty,
        employeeCode = employeeCode,
        deviceId = deviceId,
        initials = initials,
    )

    fun attendanceCheckedIn(
        checkInAt: Instant = LocalDate.of(2026, 10, 19)
            .atTime(LocalTime.of(9, 10))
            .toInstant(ZoneOffset.ofHours(3)),
        lat: Double = 30.5593,
        lng: Double = 31.0106,
    ): Attendance = Attendance(
        status = AttendanceStatus.CheckedIn,
        checkInAt = checkInAt,
        checkInLat = lat,
        checkInLng = lng,
    )

    fun attendanceCheckedOut(): Attendance = Attendance(
        status = AttendanceStatus.CheckedOut,
    )

    fun connectionOffline(pendingSyncCount: Int = 2): ConnectionStatus = ConnectionStatus(
        state = ConnectionState.Offline,
        pendingSyncCount = pendingSyncCount,
    )

    fun connectionOnline(): ConnectionStatus = ConnectionStatus(
        state = ConnectionState.Online,
        pendingSyncCount = 0,
    )

    fun activeVisit(): Visit = VisitSamples.planned(
        id = "active-1",
        placeName = "عيادة د. محمود عبد الرحمن",
        specialty = "استشاري باطنة",
        classCategory = "A",
        durationLabel = "04:35 د",
    )

    fun todayVisits(): List<Visit> = listOf(
        VisitSamples.unplanned(id = "today-1", placeName = "د. هاني المسيري", account = "عيادة باطنة"),
        VisitSamples.unplanned(id = "today-2", placeName = "د. سارة أحمد", account = "عيادة أطفال"),
    )
}
