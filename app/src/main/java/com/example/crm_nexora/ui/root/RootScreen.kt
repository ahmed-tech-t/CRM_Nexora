package com.example.crm_nexora.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crm_nexora.domain.utils.Resource
import com.example.crm_nexora.navigation.Graph
import com.example.crm_nexora.ui.components.chrome.LanguageWrapper
import com.example.crm_nexora.ui.navigation.AppNavGraph


@Composable
fun RootScreen(
    viewModel: RootViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val resource = state.bootstrap) {
        is Resource.Loading -> {
           //TODO SplashScreen()
        }

        is Resource.Success -> {
            val bootstrap = resource.data ?: return

            LanguageWrapper(languageCode = viewModel.languageState) {
                AppNavGraph(
                    startDestination = bootstrap.startGraph,
                )
            }
        }

        else -> {
            resource.data ?: viewModel.fallbackBootstrap()
            LanguageWrapper(languageCode = viewModel.languageState) {
                AppNavGraph(
                    startDestination = Graph.Auth,
                )
            }
        }
    }
}