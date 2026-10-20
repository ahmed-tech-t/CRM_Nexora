package com.example.crm_nexora.ui.screens.unplannedvisit.models

import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.domain.models.VisitType
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.models.GiftProductUiModel
import com.example.crm_nexora.ui.models.VisitUi
import com.example.crm_nexora.ui.preview.VisitSamples
import java.time.LocalDate
import java.time.LocalTime

object UnplannedVisitFakes {
    val cityOptions: List<String> = listOf("شبين الكوم", "منوف", "قويسنا")
    val districtOptions: List<String> = listOf("البر الشرقى", "البر الغربى", "وسط البلد")
    val entityTypeOptions: List<String> = listOf("عيادة خاصة", "مستشفى", "صيدلية")
    val placeOptions: List<String> = listOf(
        "عيادة د. محمود عبد الرحمن",
        "عيادة د. هاني المسيري",
        "مستشفى الجامعة",
    )
    val specialtyOptions: List<String> = listOf(
        "باطنة وجهاز هضمي",
        "أطفال",
        "جلدية",
    )
    val supervisorOptions: List<String> = listOf("د. وائل نصار", "د. سارة أحمد", "بدون مشرف")

    val giftCatalog: List<GiftProductUiModel> by lazy {
        listOf(
            VisitSamples.giftItem(
                id = "gift-catalog-1",
                name = "مج حراري + قلم معدني بشعار الشركة",
                quantity = 1,
                remainingLabel = "الرصيد المتاح في عهدتك الآن: 20 قطعة",
            ).toUi(),
            VisitSamples.giftItem(
                id = "gift-catalog-2",
                name = "دفتر ملاحظات جلد فاخر",
                quantity = 1,
                remainingLabel = "الرصيد المتاح في عهدتك الآن: 12 قطعة",
            ).toUi(),
        )
    }

    val productCatalog: List<GiftProductUiModel> by lazy {
        listOf(
            VisitSamples.productItem(
                id = "product-catalog-1",
                name = "GastroCure 40mg (Sample)",
                quantity = 1,
                remainingLabel = "الرصيد المتاح في عهدتك الآن: 20 علبة",
            ).toUi(),
            VisitSamples.productItem(
                id = "product-catalog-2",
                name = "Pediamol Syrup (Sample)",
                quantity = 1,
                remainingLabel = "الرصيد المتاح في عهدتك الآن: 9 علب",
            ).toUi(),
        )
    }

    fun initialVisit(): VisitUi = VisitSamples.unplanned(
        id = "unplanned-draft",
        placeName = "عيادة د. محمود عبد الرحمن",
        account = "عيادة خاصة",
        specialty = "باطنة وجهاز هضمي",
    ).toUi().copy(
        type = VisitType.Unplanned,
        date = LocalDate.of(2026, 10, 17),
        time = LocalTime.of(10, 30),
        city = "شبين الكوم",
        region = "البر الشرقى",
        classCategory = "A",
        shift = Shift.Pm,
        isDoubleVisit = true,
        supervisorName = "د. وائل نصار",
        address = "شبين الكوم - برج الأطباء",
        giftsQty = 1,
        productsQty = 3,
    )

    fun initialGiftItems(): List<GiftProductUiModel> = listOf(
        VisitSamples.giftItem(
            id = "gift-1",
            name = "مج حراري + قلم معدني بشعار الشركة",
            quantity = 1,
            remainingLabel = "المتبقي بعهدتك بعد الصرف: 4 قطع",
        ).toUi(),
    )

    fun initialProductItems(): List<GiftProductUiModel> = listOf(
        VisitSamples.productItem(
            id = "product-1",
            name = "GastroCure 40mg (Sample)",
            quantity = 2,
            remainingLabel = "المتبقي بعهدتك بعد الصرف: 18 علبة",
        ).toUi(),
        VisitSamples.productItem(
            id = "product-2",
            name = "Pediamol Syrup (Sample)",
            quantity = 1,
            remainingLabel = "المتبقي بعهدتك بعد الصرف: 9 علب",
        ).toUi(),
    )

    fun catalogFor(kind: QuantityKind): List<GiftProductUiModel> = when (kind) {
        QuantityKind.Gift -> giftCatalog
        QuantityKind.Product -> productCatalog
    }
}
