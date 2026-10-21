package com.example.crm_nexora.ui.root


import androidx.lifecycle.viewModelScope
import com.example.crm_nexora.data.local.AppSettingsManager
import com.example.crm_nexora.domain.models.AppBootstrap
import com.example.crm_nexora.domain.repo.AuthRepo
import com.example.crm_nexora.domain.repo.local.UserLocalRepo
import com.example.crm_nexora.domain.usecase.BootstrapUseCase
import com.example.crm_nexora.domain.utils.Constants.DEFAULT_LANG
import com.example.crm_nexora.domain.utils.Resource
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.domain.utils.UiText
import com.example.crm_nexora.navigation.AppRoute
import com.example.crm_nexora.navigation.Graph
import com.example.crm_nexora.ui.common.BaseViewModel
import com.example.crm_nexora.data.local.session.AuthSessionManager
import com.propertyadvisor.propertyadvisor.ui.screens.root.main.RootState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "RootViewModel"

@HiltViewModel
class RootViewModel @Inject constructor(
    private val authSessionManager: AuthSessionManager,
    private val appSettingsManager: AppSettingsManager,
    private val bootstrapUseCase: BootstrapUseCase,
    private val authRepo: AuthRepo,
    private val userLocalRepo: UserLocalRepo,
) : BaseViewModel() {

    val languageState: String // Use 'val' if it's read-only, or 'var' if you add a setter
        get() = appSettingsManager.getLanguageSync()
    private val _state = MutableStateFlow(RootState())
    val state: StateFlow<RootState> = _state.asStateFlow()
    var sessionObserverJop: Job? = null

    /** Set when a notification is tapped before bootstrap finishes (cold start). */
    private var pendingOpenNotifications = false

    init {
        bootstrap()
    }


    fun onEvent(event: RootEvents) {
        when (event) {
            RootEvents.RetryBootstrap -> bootstrap()
            RootEvents.OnNotificationTapped -> {
                if (_state.value.bootstrap is Resource.Success) openNotifications()
                else pendingOpenNotifications = true
            }
        }
    }

    /** Notifications requires auth; guests just stay on their start screen. */
    private fun openNotifications() {
        pendingOpenNotifications = false
      //  if (authSessionManager.isAuthenticated()) sendEvent(UiEvent.Root.OpenNotifications)
    }

    private fun bootstrap() {
        viewModelScope.launch {
            _state.update { it.copy(bootstrap = Resource.Loading()) }

            val bootstrapDeferred = async { runCatching { bootstrapUseCase() } }
            val result = bootstrapDeferred.await()
            result
                .onSuccess { data ->
                    observeSession()
                    _state.update { it.copy(bootstrap = Resource.Success(data)) }
                    if (pendingOpenNotifications) openNotifications()
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            bootstrap = Resource.Error(
                                message = throwable.message ?: "Bootstrap failed",
                                data = fallbackBootstrap(),
                            )
                        )
                    }
                }
        }
    }

    private fun observeSession() {
        sessionObserverJop?.cancel()
        sessionObserverJop = viewModelScope.launch {
            authSessionManager.sessionExpired.collect { message ->
                sendEvent(
                    UiEvent.NavigateGraph(
                        route = Graph.Auth,
                        authStartDestination = AppRoute.Login,
                    )
                )
                if (!message.isNullOrBlank()) {
                    // Let Auth shell compose its SnackbarHost before showing the message.
                    delay(150.milliseconds)
                    sendEvent(UiEvent.ShowSnackBar(UiText.DynamicText(message)))
                }
            }
        }
    }



    fun fallbackBootstrap() = AppBootstrap(
        language = DEFAULT_LANG,
        isAuthenticated = false,
        user = null,
        showOnBoarding = true,
        startGraph = Graph.Auth,
    )

    override fun onCleared() {
        super.onCleared()
    }
}