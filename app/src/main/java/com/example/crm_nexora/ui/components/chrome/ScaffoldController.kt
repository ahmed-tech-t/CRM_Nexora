package com.example.crm_nexora.ui.components.chrome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
class ScaffoldController {
    var topBar by mutableStateOf<(@Composable () -> Unit)?>(null)
        private set
    var bottomBar by mutableStateOf<(@Composable () -> Unit)?>(null)
        private set
    var bottomBarVisible by mutableStateOf(true)
        private set

    var topBarVisible by mutableStateOf(true)
        private set
    var fab by mutableStateOf<(@Composable () -> Unit)?>(null)
        private set

    fun updateScaffold(
        showBottomBar: Boolean? = null,
        topBar: (@Composable () -> Unit)? = null,
        bottomBar: (@Composable () -> Unit)? = null,
        fab: (@Composable () -> Unit)? = null,
    ) {
        topBar?.let { this.topBar = it }
        bottomBar?.let { this.bottomBar = it }
        showBottomBar?.let { bottomBarVisible = it }
        fab?.let { this.fab = it }
    }

    fun updateTopBarVisible(visible: Boolean) {
        if (topBarVisible != visible) {
            topBarVisible = visible
        }
    }

    fun clear() {
        topBar = null
        bottomBar = null
        topBarVisible = true
        bottomBarVisible = true
        fab = null
    }
}

val LocalScaffoldController = staticCompositionLocalOf<ScaffoldController> {
    error("No ScaffoldController provided! Wrap your root layout in a CompositionLocalProvider.")
}
