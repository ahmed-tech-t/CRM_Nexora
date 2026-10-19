package com.example.crm_nexora.ui.screens.statics

import com.example.crm_nexora.ui.screens.statics.main.StaticsViewModel
import com.example.crm_nexora.ui.screens.statics.models.StaticsAccountCategoryUi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StaticsViewModelTest {

    @Test
    fun initialState_hasVisitMetricCountsFromViewModel() {
        val state = StaticsViewModel().state.value

        assertEquals(94, state.completedPlan)
        assertEquals(26, state.todayVisits)
        assertEquals(14, state.unplannedVisits)
    }

    @Test
    fun initialState_hasTwoCustodyItems() {
        val state = StaticsViewModel().state.value

        assertEquals(2, state.custodyItems.size)
        val samples = state.custodyItems.single { it.id == "samples" }
        val gifts = state.custodyItems.single { it.id == "gifts" }
        assertEquals(85, samples.remaining)
        assertEquals(165, samples.dispatched)
        assertEquals(250, samples.receivedQuantity)
        assertEquals(6, samples.skuCount)
        assertEquals(28, gifts.remaining)
        assertEquals(62, gifts.dispatched)
        assertEquals(90, gifts.receivedQuantity)
        assertEquals(4, gifts.skuCount)
    }

    @Test
    fun initialState_hasFourAccountCategories() {
        val state = StaticsViewModel().state.value

        assertEquals(4, state.accountCategories.size)
        assertEquals(62, state.accountCategories.filterIsInstance<StaticsAccountCategoryUi.Clinic>().single().count)
        assertEquals(18, state.accountCategories.filterIsInstance<StaticsAccountCategoryUi.Pharmacy>().single().count)
        assertEquals(9, state.accountCategories.filterIsInstance<StaticsAccountCategoryUi.Hospital>().single().count)
        assertEquals(5, state.accountCategories.filterIsInstance<StaticsAccountCategoryUi.HealthUnit>().single().count)
    }

    @Test
    fun initialState_labelsAreNonBlankResIds() {
        val state = StaticsViewModel().state.value

        assertTrue(state.custodyItems.all { it.titleRes != 0 && it.receivedFormatRes != 0 })
        assertTrue(state.accountCategories.all { it.labelRes != 0 })
    }
}
