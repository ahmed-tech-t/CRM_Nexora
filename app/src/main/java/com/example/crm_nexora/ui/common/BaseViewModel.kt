package com.example.crm_nexora.ui.common

import androidx.lifecycle.ViewModel
import com.example.crm_nexora.domain.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Shared ViewModel base — one-shot [uiEvent] channel for navigation / snackbars / etc.
 */
abstract class BaseViewModel : ViewModel() {

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    protected fun sendEvent(event: UiEvent) {
        _uiEvent.trySend(event)
    }
}
