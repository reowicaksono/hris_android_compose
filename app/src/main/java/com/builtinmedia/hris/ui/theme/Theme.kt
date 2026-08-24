package com.builtinmedia.hris.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val lightColorScheme = lightColorScheme(
    primary = HrisColors.Primary,
    onPrimary = HrisColors.Surface,
    primaryContainer = HrisColors.Sky,
    onPrimaryContainer = HrisColors.PrimaryDark,
    background = HrisColors.Background,
    onBackground = HrisColors.TextPrimary,
    surface = HrisColors.Surface,
    onSurface = HrisColors.TextPrimary,
    surfaceVariant = HrisColors.SkySoft,
    onSurfaceVariant = HrisColors.TextSecondary,
    outline = HrisColors.Border,
    error = HrisColors.Error,
    onError = HrisColors.Surface,
    errorContainer = HrisColors.ErrorBg
)

private val darkColorScheme = darkColorScheme(
    primary = HrisColors.PrimaryLight,
    onPrimary = HrisColors.TextPrimary,

    primaryContainer = HrisColors.PrimaryDark,
    onPrimaryContainer = HrisColors.Sky,

    background = HrisColors.DarkBackground,
    onBackground = HrisColors.DarkTextPrimary,

    surface = HrisColors.DarkSurface,
    onSurface = HrisColors.DarkTextPrimary,

    surfaceVariant = HrisColors.DarkSurfaceVariant,
    onSurfaceVariant = HrisColors.DarkTextSecondary,

    outline = HrisColors.DarkBorder,

    error = HrisColors.ErrorLight,
    onError = HrisColors.DarkTextPrimary,

    errorContainer = HrisColors.ErrorDarkBg,
    onErrorContainer = HrisColors.ErrorLight
)
@Composable
fun HrisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
