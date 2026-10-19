package com.example.crm_nexora.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NexoraPrimary,
    onPrimary = NexoraOnPrimary,
    secondary = NexoraTextSecondary,
    onSecondary = NexoraOnPrimary,
    tertiary = NexoraPrimaryDeep,
    background = NexoraSurface,
    onBackground = NexoraTextPrimary,
    surface = NexoraSurface,
    onSurface = NexoraTextPrimary,
    surfaceVariant = NexoraSurfaceMuted,
    onSurfaceVariant = NexoraTextSecondary,
    outline = NexoraBorder,
    error = NexoraDanger,
    onError = NexoraOnPrimary,
)

private val DarkColorScheme = darkColorScheme(
    primary = NexoraPrimaryFocus,
    onPrimary = NexoraOnPrimary,
    secondary = PurpleGrey80,
    onSecondary = NexoraNavy,
    tertiary = Pink80,
    background = NexoraNavy,
    onBackground = NexoraOnPrimary,
    surface = NexoraNavy,
    onSurface = NexoraOnPrimary,
    surfaceVariant = NexoraTextPrimary,
    onSurfaceVariant = PurpleGrey80,
    outline = NexoraTextMuted,
    error = NexoraDanger,
    onError = NexoraOnPrimary,
)

@Composable
fun CRM_NexoraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
