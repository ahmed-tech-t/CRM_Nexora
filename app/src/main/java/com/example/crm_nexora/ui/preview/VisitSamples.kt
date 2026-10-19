package com.example.crm_nexora.ui.preview

import com.example.crm_nexora.domain.models.QuantityItem
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.domain.models.SyncStatus
import com.example.crm_nexora.domain.models.Visit
import com.example.crm_nexora.domain.models.VisitMode
import com.example.crm_nexora.domain.models.VisitType
import java.time.LocalDate
import java.time.LocalTime

/** Shared sample visits for Previews, catalog, and UI tests. */
object VisitSamples {

    val sampleDate: LocalDate = LocalDate.of(2026, 10, 19)

    fun planned(
        id: String = "v1",
        placeName: String = "عيادة د. محمود عبد الرحمن",
        account: String = "د. محمود",
        specialty: String = "استشاري باطنة",
        classCategory: String? = "A",
        durationLabel: String? = "04:35 د",
    ): Visit = Visit(
        id = id,
        date = sampleDate,
        time = LocalTime.of(9, 30),
        city = "شبين الكوم",
        region = "البر الشرقى",
        account = account,
        placeName = placeName,
        specialty = specialty,
        shift = Shift.Am,
        type = VisitType.Planned,
        visitMode = VisitMode.Single,
        classCategory = classCategory,
        syncStatus = SyncStatus.NotSynced,
        durationLabel = durationLabel,
    )

    fun unplanned(
        id: String = "d1",
        placeName: String = "د. هاني المسيري",
        account: String = "عيادة باطنة",
        specialty: String = "باطنة",
    ): Visit = Visit(
        id = id,
        date = sampleDate,
        time = LocalTime.of(11, 0),
        city = "شبين الكوم",
        address = "\uD83D\uDCCD شارع الجلاء - برج الأطباء",
        placeName = placeName,
        account = account,
        specialty = specialty,
        shift = Shift.Pm,
        type = VisitType.Unplanned,
        syncStatus = SyncStatus.NotSynced,
    )

    fun planning(
        id: String = "c1",
        placeName: String = "عيادة خاصة",
        specialty: String = "باطنة",
        classCategory: String? = "A",
        visitMode: VisitMode? = VisitMode.Single,
        supervisorName: String? = null,
    ): Visit = Visit(
        id = id,
        date = sampleDate,
        time = LocalTime.of(10, 0),
        city = "شبين الكوم",
        region = "البر الشرقى",
        account = "عيادة",
        placeName = placeName,
        specialty = specialty,
        shift = Shift.Am,
        type = VisitType.Planning,
        visitMode = visitMode,
        supervisorName = supervisorName,
        classCategory = classCategory,
        syncStatus = SyncStatus.NotSynced,
    )

    fun planningDouble(
        placeName: String = "عيادة د. محمود عبد الرحمن",
        specialty: String = "باطنة وجهاز هضمي",
        supervisorName: String = "د. وائل نصار",
    ): Visit = planning(
        placeName = placeName,
        specialty = specialty,
        classCategory = "A",
        visitMode = VisitMode.Double,
        supervisorName = supervisorName,
    )

    fun giftItem(
        id: String = "g1",
        name: String = "مج حراري + قلم معدني بشعار الشركة",
        quantity: Int = 2,
        remainingLabel: String? = "المتبقي بعهدتك بعد الصرف: 4 قطع",
    ): QuantityItem = QuantityItem(
        id = id,
        name = name,
        quantity = quantity,
        kind = QuantityKind.Gift,
        remainingLabel = remainingLabel,
    )

    fun productItem(
        id: String = "m1",
        name: String = "GastroCure 40mg (Sample)",
        quantity: Int = 1,
        remainingLabel: String? = "المتبقي بعهدتك بعد الصرف: 18 علبة",
    ): QuantityItem = QuantityItem(
        id = id,
        name = name,
        quantity = quantity,
        kind = QuantityKind.Product,
        remainingLabel = remainingLabel,
    )
}
