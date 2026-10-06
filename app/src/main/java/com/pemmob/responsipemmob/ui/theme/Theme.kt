package com.pemmob.responsipemmob.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = GempaPrimary,
    onPrimary = GempaOnPrimary,
    primaryContainer = GempaPrimaryContainer,
    onPrimaryContainer = GempaOnPrimaryContainer,
    secondary = GempaSecondary,
    onSecondary = GempaOnSecondary,
    secondaryContainer = GempaSecondaryContainer,
    onSecondaryContainer = GempaOnSecondaryContainer,
    background = GempaBackground,
    onBackground = GempaOnBackground,
    surface = GempaSurface,
    onSurface = GempaOnSurface,
    surfaceVariant = GempaSurfaceVariant,
    onSurfaceVariant = GempaOnSurfaceVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = GempaPrimaryDark,
    onPrimary = GempaOnPrimaryDark,
    primaryContainer = GempaPrimaryContainerDark,
    onPrimaryContainer = GempaOnPrimaryContainerDark,
    secondary = GempaSecondaryDark,
    onSecondary = GempaOnSecondaryDark,
    secondaryContainer = GempaSecondaryContainerDark,
    onSecondaryContainer = GempaOnSecondaryContainerDark,
    background = GempaBackgroundDark,
    onBackground = GempaOnBackgroundDark,
    surface = GempaSurfaceDark,
    onSurface = GempaOnSurfaceDark,
    surfaceVariant = GempaSurfaceVariantDark,
    onSurfaceVariant = GempaOnSurfaceVariantDark
)

@Composable
fun ResponsipemmobTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}