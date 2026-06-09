package com.finup.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF7C4DFF),
    background = Color(0xFF0F111A),
    surface = Color(0xFF161926),
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    secondary = Color(0xFF00E676),
    tertiary = Color(0xFFFF5252)
)

private val LightColorScheme = DarkColorScheme

@Composable
fun FinUpTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
   val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}