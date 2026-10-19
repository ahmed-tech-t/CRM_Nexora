package com.example.crm_nexora.ui.screens.sampleandgifts

import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsEvents
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsTab
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsViewModel
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SampleAndGiftsViewModelTest {

    @Test
    fun initialState_selectedTabIsSamples() {
        val state = SampleAndGiftsViewModel().state.value

        assertEquals(SampleAndGiftsTab.Samples, state.selectedTab)
    }

    @Test
    fun initialState_hasSamplesAndGifts() {
        val state = SampleAndGiftsViewModel().state.value
        val samples = state.items.filter { it.type == SampleAndGiftsItemType.Sample }
        val gifts = state.items.filter { it.type == SampleAndGiftsItemType.Gift }

        assertEquals(6, samples.size)
        assertEquals(4, gifts.size)
    }

    @Test
    fun initialState_hasLowStockSample() {
        val state = SampleAndGiftsViewModel().state.value
        val lowStock = state.items.filter {
            it.type == SampleAndGiftsItemType.Sample && it.isLowStock
        }

        assertTrue(lowStock.isNotEmpty())
        assertTrue(lowStock.all { it.remainingUnit.isNotBlank() })
    }

    @Test
    fun initialState_giftsAreNotLowStock() {
        val state = SampleAndGiftsViewModel().state.value

        assertTrue(
            state.items
                .filter { it.type == SampleAndGiftsItemType.Gift }
                .all { !it.isLowStock },
        )
    }

    @Test
    fun selectTab_gifts_updatesSelectedTab() {
        val vm = SampleAndGiftsViewModel()

        vm.onEvent(SampleAndGiftsEvents.SelectTab(SampleAndGiftsTab.Gifts))

        assertEquals(SampleAndGiftsTab.Gifts, vm.state.value.selectedTab)
    }

    @Test
    fun selectTab_samples_afterGifts_restoresSamples() {
        val vm = SampleAndGiftsViewModel()

        vm.onEvent(SampleAndGiftsEvents.SelectTab(SampleAndGiftsTab.Gifts))
        vm.onEvent(SampleAndGiftsEvents.SelectTab(SampleAndGiftsTab.Samples))

        assertEquals(SampleAndGiftsTab.Samples, vm.state.value.selectedTab)
    }

    @Test
    fun items_haveNonBlankNamesAndIds() {
        val state = SampleAndGiftsViewModel().state.value

        assertFalse(state.items.isEmpty())
        assertTrue(state.items.all { it.id.isNotBlank() && it.name.isNotBlank() })
    }
}
