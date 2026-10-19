package com.example.crm_nexora.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.ui.components.chrome.BottomNavBar
import com.example.crm_nexora.ui.components.chrome.LocalScaffoldController
import com.example.crm_nexora.ui.components.chrome.ScaffoldComponent
import com.example.crm_nexora.ui.components.chrome.ScaffoldController
import com.example.crm_nexora.ui.components.chrome.defaultBottomNavItems
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme

/**
 * Shared chrome for bottom-nav tabs — owns [BottomNavBar] and snackbar host.
 */
@Composable
fun MainShell(
    selectedTabId: String,
    onTabSelected: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scaffoldController = remember { ScaffoldController() }
    CompositionLocalProvider(LocalScaffoldController provides scaffoldController) {
        ScaffoldComponent(
            snackbarHostState = snackbarHostState,
            containerColor = MaterialTheme.colorScheme.background,
            modifier = modifier.fillMaxSize(),
            topBar = {
                scaffoldController.topBar?.invoke()
            },
            bottomBar = {
                BottomNavBar(
                    items = defaultBottomNavItems(),
                    selectedId = selectedTabId,
                    onItemSelected = onTabSelected,
                )
            },
            content = content,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainShellPreview() {
    CRM_NexoraTheme {
        MainShell(
            selectedTabId = "home",
            onTabSelected = {},
            snackbarHostState = remember { SnackbarHostState() },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "MainShell Content")
            }
        }
    }
}
