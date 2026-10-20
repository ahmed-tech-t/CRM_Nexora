package com.example.crm_nexora.ui.screens.unplannedvisit

import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitOverlay
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitStep
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitViewModel
import com.example.crm_nexora.ui.screens.unplannedvisit.models.UnplannedVisitFakes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UnplannedVisitViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialState_isEntityStep_withVisitDraft() {
        val state = UnplannedVisitViewModel().state.value

        assertEquals(UnplannedVisitStep.Entity, state.currentStep)
        assertTrue(state.visit.placeName.isNotBlank())
        assertEquals(Shift.Pm, state.visit.shift)
        assertTrue(state.visit.isDoubleVisit)
        assertEquals(1, state.giftItems.size)
        assertEquals(2, state.productItems.size)
        assertEquals(UnplannedVisitOverlay.None, state.overlay)
    }

    @Test
    fun next_advancesThroughSteps_andStopsAtResult() {
        val vm = UnplannedVisitViewModel()

        vm.onEvent(UnplannedVisitEvents.Next)
        assertEquals(UnplannedVisitStep.GiftsProducts, vm.state.value.currentStep)

        vm.onEvent(UnplannedVisitEvents.Next)
        assertEquals(UnplannedVisitStep.Result, vm.state.value.currentStep)

        vm.onEvent(UnplannedVisitEvents.Next)
        assertEquals(UnplannedVisitStep.Result, vm.state.value.currentStep)
    }

    @Test
    fun previous_fromEntity_staysOnEntity() {
        val vm = UnplannedVisitViewModel()

        vm.onEvent(UnplannedVisitEvents.Previous)

        assertEquals(UnplannedVisitStep.Entity, vm.state.value.currentStep)
    }

    @Test
    fun previous_fromResult_returnsToGifts() {
        val vm = UnplannedVisitViewModel()
        vm.onEvent(UnplannedVisitEvents.Next)
        vm.onEvent(UnplannedVisitEvents.Next)

        vm.onEvent(UnplannedVisitEvents.Previous)

        assertEquals(UnplannedVisitStep.GiftsProducts, vm.state.value.currentStep)
    }

    @Test
    fun selectShift_updatesVisit() {
        val vm = UnplannedVisitViewModel()

        vm.onEvent(UnplannedVisitEvents.SelectShift(Shift.Am))

        assertEquals(Shift.Am, vm.state.value.visit.shift)
    }

    @Test
    fun selectDoubleVisit_updatesVisit() {
        val vm = UnplannedVisitViewModel()

        vm.onEvent(UnplannedVisitEvents.SelectDoubleVisit(false))

        assertFalse(vm.state.value.visit.isDoubleVisit)
    }

    @Test
    fun openAddItem_showsOverlay() {
        val vm = UnplannedVisitViewModel()

        vm.onEvent(UnplannedVisitEvents.OpenAddItem(QuantityKind.Gift))

        assertTrue(vm.state.value.overlay is UnplannedVisitOverlay.AddItem)
    }

    @Test
    fun confirmAddItems_appendsGiftsAndClearsOverlay() {
        val vm = UnplannedVisitViewModel()
        val before = vm.state.value.giftItems.size
        val drafts = UnplannedVisitFakes.catalogFor(QuantityKind.Gift).take(2).map {
            it.copy(quantity = 1, quantityLabel = "1")
        }

        vm.onEvent(UnplannedVisitEvents.OpenAddItem(QuantityKind.Gift))
        vm.onEvent(UnplannedVisitEvents.ConfirmAddItems(drafts))

        assertEquals(UnplannedVisitOverlay.None, vm.state.value.overlay)
        assertEquals(before + 2, vm.state.value.giftItems.size)
        assertEquals(
            vm.state.value.giftItems.sumOf { it.quantity },
            vm.state.value.visit.giftsQty,
        )
    }

    @Test
    fun incrementGift_updatesQuantityAndVisitTotals() {
        val vm = UnplannedVisitViewModel()
        val id = vm.state.value.giftItems.first().id
        val beforeQty = vm.state.value.giftItems.first().quantity

        vm.onEvent(UnplannedVisitEvents.IncrementGift(id))

        val updated = vm.state.value.giftItems.first { it.id == id }
        assertEquals(beforeQty + 1, updated.quantity)
        assertEquals(
            vm.state.value.giftItems.sumOf { it.quantity },
            vm.state.value.visit.giftsQty,
        )
    }

    @Test
    fun endVisit_emitsNavigationUp() = runTest(UnconfinedTestDispatcher()) {
        val vm = UnplannedVisitViewModel()
        val event = async { vm.uiEvent.first { it is UiEvent.NavigationUp } }

        vm.onEvent(UnplannedVisitEvents.EndVisit)

        assertEquals(UiEvent.NavigationUp, event.await())
    }

    @Test
    fun back_emitsNavigationUp() = runTest(UnconfinedTestDispatcher()) {
        val vm = UnplannedVisitViewModel()
        val event = async { vm.uiEvent.first() }

        vm.onEvent(UnplannedVisitEvents.Back)

        assertEquals(UiEvent.NavigationUp, event.await())
    }
}
