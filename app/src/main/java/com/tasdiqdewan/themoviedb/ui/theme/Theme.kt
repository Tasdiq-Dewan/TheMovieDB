package com.tasdiqdewan.themoviedb.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue80,
    onPrimary = DarkBlue20,
    primaryContainer = DarkBlue30,
    onPrimaryContainer = DarkBlue90,
    secondary = LightBlue,
    onSecondary = LightBlue20,
    secondaryContainer = LightBlue30,
    onSecondaryContainer = LightBlue90,
    tertiary = LightGreen,
    onTertiary = LightGreen20,
    tertiaryContainer = LightGreen30,
    onTertiaryContainer = LightGreen90,
    error = Error80,
    onError = Error20,
    errorContainer = Error30,
    onErrorContainer = Error90,
    background = DarkBlue,
    onBackground = Neutral90,
    surface = DarkBlue,
    onSurface = Neutral90,
    outline = NeutralVariant60,
    surfaceVariant = DarkBlue40,
    onSurfaceVariant = NeutralVariant80
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    onPrimary = White,
    primaryContainer = DarkBlue90,
    onPrimaryContainer = DarkBlue10,
    secondary = LightBlue,
    onSecondary = LightBlue30,
    secondaryContainer = LightBlue90,
    onSecondaryContainer = LightBlue10,
    tertiary = LightGreen,
    onTertiary = LightGreen40,
    tertiaryContainer = LightGreen90,
    onTertiaryContainer = LightGreen10,
    error = Error40,
    onError = White,
    errorContainer = Error90,
    onErrorContainer = Error10,
    background = Neutral99,
    onBackground = Neutral10,
    surface = Neutral99,
    onSurface = Neutral10,
    outline = NeutralVariant50,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TheMovieDBTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}