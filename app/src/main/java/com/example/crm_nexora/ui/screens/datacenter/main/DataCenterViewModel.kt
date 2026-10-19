package com.example.crm_nexora.ui.screens.datacenter.main

import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.ui.common.BaseViewModel
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


@HiltViewModel
class DataCenterViewModel @Inject constructor() : BaseViewModel() {


    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<DataCenterState> = _state.asStateFlow()

    fun onEvent(event: DataCenterEvents) {
        when (event) {
            DataCenterEvents.OnBack -> sendUiEvent(UiEvent.NavigationUp)
            is DataCenterEvents.OnSync -> syncCategory(event.categoryId)
        }
    }

    private fun syncCategory(categoryId: String) {
        //Call to server to sync category
    }

    companion object {

        fun initialState(): DataCenterState = DataCenterState(
            items = DataCenterSyncItemUi.items(),
        )
    }
}
