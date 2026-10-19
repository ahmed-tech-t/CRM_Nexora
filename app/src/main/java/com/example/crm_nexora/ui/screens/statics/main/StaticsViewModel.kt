package com.example.crm_nexora.ui.screens.statics.main

import com.example.crm_nexora.ui.common.BaseViewModel
import com.example.crm_nexora.ui.screens.statics.models.StaticsAccountCategoryUi
import com.example.crm_nexora.ui.screens.statics.models.StaticsGiftsFakes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class StaticsViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<StaticsState> = _state.asStateFlow()

    fun onEvent(event: StaticsEvents) {
        // Display-only screen for now — no intents.
    }

    companion object {
        fun initialState(): StaticsState = StaticsState(
            completedPlan = 94,
            todayVisits = 26,
            unplannedVisits = 14,
            custodyItems = StaticsGiftsFakes.items,
            accountCategories = StaticsAccountCategoryUi.samples,
        )
    }
}
