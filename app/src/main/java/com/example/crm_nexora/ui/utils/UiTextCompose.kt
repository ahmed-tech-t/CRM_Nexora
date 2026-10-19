package com.example.crm_nexora.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.crm_nexora.domain.utils.UiText

@Composable
fun UiText.asString(): String {
    val context = LocalContext.current
    return asString(context)
}
