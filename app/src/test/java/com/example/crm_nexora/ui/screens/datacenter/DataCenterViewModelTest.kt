package com.example.crm_nexora.ui.screens.datacenter

import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterEvents
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterViewModel
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncItemUi
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DataCenterViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialState_hasFourIdleItems() {
        val vm = DataCenterViewModel()
        val state = vm.state.value

        assertEquals(4, state.items.size)
        assertTrue(state.items.all { it.status is DataCenterSyncStatus.Idle })
        assertEquals(DataCenterSyncItemUi.ID_FULL, state.items.first().id)
    }

    @Test
    fun onBack_emitsNavigationUp() = runTest {
        val vm = DataCenterViewModel()
        val event = async { vm.uiEvent.first() }

        vm.onEvent(DataCenterEvents.OnBack)

        assertTrue(event.await() is UiEvent.NavigationUp)
    }
}
