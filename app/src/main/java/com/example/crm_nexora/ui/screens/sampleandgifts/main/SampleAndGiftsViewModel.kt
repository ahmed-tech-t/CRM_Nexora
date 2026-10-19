package com.example.crm_nexora.ui.screens.sampleandgifts.main

import com.example.crm_nexora.ui.common.BaseViewModel
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsFakes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SampleAndGiftsViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<SampleAndGiftsState> = _state.asStateFlow()

    fun onEvent(event: SampleAndGiftsEvents) {
        when (event) {
            is SampleAndGiftsEvents.SelectTab -> {
                _state.update { it.copy(selectedTab = event.tab) }
            }
        }
    }

    companion object {
        fun initialState(): SampleAndGiftsState = SampleAndGiftsState(
            selectedTab = SampleAndGiftsTab.Samples,
            items = SampleAndGiftsFakes.items,
        )
    }
}
