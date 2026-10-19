package com.example.crm_nexora.domain.utils

import com.example.crm_nexora.navigation.AppRoute

sealed class UiEvent {
    data class Navigate(val destination: AppRoute) : UiEvent()
    data class NavigateGraph(
        val route: String,
        val authStartDestination: String? = null,
    ) : UiEvent()

    data object NavigationUp : UiEvent()
    data object FinishActivity : UiEvent()
    data object HideKeyboard : UiEvent()
    data class ShowSnackBar(val text: UiText) : UiEvent()
    data class OpenUrl(val url: String) : UiEvent()
}
