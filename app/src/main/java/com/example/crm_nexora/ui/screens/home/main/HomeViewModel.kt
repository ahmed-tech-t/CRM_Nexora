package com.example.crm_nexora.ui.screens.home.main

import com.example.crm_nexora.R
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.domain.utils.UiText
import com.example.crm_nexora.ui.common.BaseViewModel
import com.example.crm_nexora.ui.components.chrome.nav_drawer.NavDrawerItem
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.ui.preview.HomeSamples
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnMenuClick -> {
                _state.update { it.copy(isDrawerOpen = true) }
            }
            HomeEvents.OnDrawerDismiss -> {
                _state.update { it.copy(isDrawerOpen = false) }
            }
            is HomeEvents.OnDrawerItem -> {
                _state.update { it.copy(isDrawerOpen = false) }
                when (event.item) {
                    NavDrawerItem.InfoCenter -> {
                        sendUiEvent(UiEvent.Navigate(AppRoute.DataCenter))
                    }
                    else -> {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(UiText.StringResource(R.string.placeholder_coming_soon)),
                        )
                    }
                }
            }
            HomeEvents.OnStartShift,
            HomeEvents.OnEndShift,
            HomeEvents.OnSyncClick,
            HomeEvents.OnActiveContinue,
            HomeEvents.OnActiveCancel,
            is HomeEvents.OnStartVisit,
            HomeEvents.OnTodayMoreClick -> {
                // TODO: wire shift GPS, sync, visit actions later
            }
        }
    }

    companion object {
        fun initialState(): HomeState = HomeState(
            user = HomeSamples.user().toUi(),
            attendance = HomeSamples.attendanceCheckedIn().toUi().copy(
                checkInLocationLabel = "ميدان شرف",
            ),
            connection = HomeSamples.connectionOffline().toUi(),
            activeVisit = HomeSamples.activeVisit().toUi(),
            todayVisits = HomeSamples.todayVisits().map { it.toUi() },
            isDrawerOpen = false,
        )
    }
}
