package com.example.crm_nexora.ui.screens.home

import com.example.crm_nexora.domain.models.AttendanceStatus
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.preview.HomeSamples
import com.example.crm_nexora.ui.components.chrome.nav_drawer.NavDrawerItem
import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.ui.screens.home.main.HomeEvents
import com.example.crm_nexora.ui.screens.home.main.HomeViewModel
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
class HomeViewModelTest {

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
    fun initialState_usesHomeSamples() {
        val vm = HomeViewModel()
        val state = vm.state.value

        assertEquals(HomeSamples.user().displayName, state.user.displayName)
        assertEquals(AttendanceStatus.CheckedIn, state.attendance.status)
        assertTrue(state.attendance.checkInTimeLabel.isNotBlank())
        assertEquals(2, state.connection.pendingCount)
        assertEquals(HomeSamples.activeVisit().placeName, state.activeVisit?.placeName)
        assertEquals(2, state.todayVisits.size)
        assertFalse(state.isDrawerOpen)
    }

    @Test
    fun shiftEvents_areStubs_andDoNotCrash() {
        val vm = HomeViewModel()
        val before = vm.state.value

        vm.onEvent(HomeEvents.OnStartShift)
        vm.onEvent(HomeEvents.OnEndShift)

        assertEquals(before, vm.state.value)
    }

    @Test
    fun onMenuClick_opensDrawer() {
        val vm = HomeViewModel()

        vm.onEvent(HomeEvents.OnMenuClick)

        assertTrue(vm.state.value.isDrawerOpen)
    }

    @Test
    fun onDrawerDismiss_closesDrawer() {
        val vm = HomeViewModel()
        vm.onEvent(HomeEvents.OnMenuClick)

        vm.onEvent(HomeEvents.OnDrawerDismiss)

        assertFalse(vm.state.value.isDrawerOpen)
    }

    @Test
    fun onDrawerItem_closesDrawer_andEmitsSnackBar() = runTest(UnconfinedTestDispatcher()) {
        val vm = HomeViewModel()
        val snackBar = async { vm.uiEvent.first { it is UiEvent.ShowSnackBar } }

        vm.onEvent(HomeEvents.OnMenuClick)
        vm.onEvent(HomeEvents.OnDrawerItem(NavDrawerItem.Planning))

        assertFalse(vm.state.value.isDrawerOpen)
        assertTrue(snackBar.await() is UiEvent.ShowSnackBar)
    }

    @Test
    fun onDrawerItem_infoCenter_navigatesToDataCenter() = runTest(UnconfinedTestDispatcher()) {
        val vm = HomeViewModel()
        val nav = async {
            vm.uiEvent.first { it is UiEvent.Navigate }
        }

        vm.onEvent(HomeEvents.OnMenuClick)
        vm.onEvent(HomeEvents.OnDrawerItem(NavDrawerItem.InfoCenter))

        assertFalse(vm.state.value.isDrawerOpen)
        val event = nav.await() as UiEvent.Navigate
        assertEquals(AppRoute.DataCenter, event.destination)
    }

    @Test
    fun onDrawerItem_unplanned_navigatesToUnplannedVisit() = runTest(UnconfinedTestDispatcher()) {
        val vm = HomeViewModel()
        val nav = async {
            vm.uiEvent.first { it is UiEvent.Navigate }
        }

        vm.onEvent(HomeEvents.OnMenuClick)
        vm.onEvent(HomeEvents.OnDrawerItem(NavDrawerItem.Unplanned))

        assertFalse(vm.state.value.isDrawerOpen)
        val event = nav.await() as UiEvent.Navigate
        assertEquals(AppRoute.UnplannedVisit, event.destination)
    }
}
