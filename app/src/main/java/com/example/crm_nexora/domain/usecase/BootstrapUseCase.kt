package com.example.crm_nexora.domain.usecase


import com.example.crm_nexora.data.local.AppSettingsManager
import com.example.crm_nexora.domain.models.AppBootstrap
import com.example.crm_nexora.domain.repo.local.AppSettingsRepo
import com.example.crm_nexora.domain.repo.local.UserLocalRepo
import com.example.crm_nexora.domain.utils.Constants.DEFAULT_LANG
import com.example.crm_nexora.navigation.Graph
import com.example.crm_nexora.data.local.session.AuthSessionManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

private const val TAG = "BootstrapUseCase"

class BootstrapUseCase @Inject constructor(
    private val authSessionManager: AuthSessionManager,
    private val appSettingsManager: AppSettingsManager,
    private val appSettingsRepo: AppSettingsRepo,
    private val userLocalRepo: UserLocalRepo,
) {
    suspend operator fun invoke(): AppBootstrap {
        authSessionManager.initialize()
        appSettingsManager.initialize()

//        val showOnBoarding = runCatching {
//            appSettingsRepo.getShowOnBoarding()
//        }.getOrDefault(true)
//
//        if (showOnBoarding) {
//            return AppBootstrap(
//                language = DEFAULT_LANG,
//                showOnBoarding = true,
//                isAuthenticated = false,
//                user = null,
//                startGraph = AppRoute.Onboarding,
//            )
//        }

        val language = runCatching {
            appSettingsRepo.getSettings().first().language
        }.getOrDefault(DEFAULT_LANG)

        val user = runCatching {
            userLocalRepo.getUser().firstOrNull()
        }.getOrNull()

        val authStatus = authSessionManager.isAuthenticated()
        val startGraph = when (authStatus) {
            true -> Graph.Main
            else -> {
                Graph.Auth
            }
        }

        return AppBootstrap(
            language = language,
            showOnBoarding = false,
            isAuthenticated = authStatus,
            user = user,
            startGraph = Graph.Main,
        )
    }
}