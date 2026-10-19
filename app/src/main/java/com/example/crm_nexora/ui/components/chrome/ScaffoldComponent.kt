package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScaffoldComponent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                SnackBarComponent(modifier = Modifier, data = data)
            }
        },
        containerColor = containerColor,
        topBar = topBar,
        bottomBar = bottomBar,
        content = { paddingValue -> content(paddingValue) })

}