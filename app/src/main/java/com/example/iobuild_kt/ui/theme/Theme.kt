package com.example.iobuild_kt.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Neutral100,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    secondary = Blue40,
    onSecondary = Neutral100,
    secondaryContainer = Blue80,
    onSecondaryContainer = Color(0xFF062D45),
    background = Neutral95,
    onBackground = Neutral10,
    surface = Neutral100,
    onSurface = Neutral10,
    surfaceVariant = Neutral90,
    onSurfaceVariant = Color(0xFF4B5563),
    error = Error40,
    onError = Neutral100,
    errorContainer = Error80,
    onErrorContainer = Color(0xFF410002),
    outline = Color(0xFFD1D5DB)
)

private val DarkColorScheme = darkColorScheme(
    primary = Green50,
    onPrimary = DarkGreen10,
    primaryContainer = DarkGreen20,
    onPrimaryContainer = Green80,
    secondary = Blue40,
    onSecondary = Color(0xFF003549),
    secondaryContainer = Color(0xFF004D6E),
    onSecondaryContainer = Blue80,
    background = Color(0xFF111827),
    onBackground = Color(0xFFF3F4F6),
    surface = Color(0xFF1F2937),
    onSurface = Color(0xFFF3F4F6),
    surfaceVariant = Color(0xFF374151),
    onSurfaceVariant = Color(0xFFD1D5DB),
    error = Error80,
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Error80,
    outline = Color(0xFF6B7280)
)

@Composable
fun IoBuildktTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
