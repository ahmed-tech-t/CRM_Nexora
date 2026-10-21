package com.example.crm_nexora.domain.utils

import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.navigation.Graph

sealed class UiEvent {
    data class Navigate(val destination: AppRoute) : UiEvent()
    data class NavigateGraph(
        val route: Graph,
        val authStartDestination: AppRoute? = null,
    ) : UiEvent()

    data object NavigationUp : UiEvent()
    data object FinishActivity : UiEvent()
    data object HideKeyboard : UiEvent()
    data class ShowSnackBar(val text: UiText) : UiEvent()
    data class OpenUrl(val url: String) : UiEvent()
}
