package com.pony.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pony.compose.extension.*

/**
 *Created by pony on 2022/6/20
 *Description->主题
 */
@Composable
fun AppCommonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val darkColorScheme = darkColorScheme(
        primary = purple200,
        onPrimary = purple,
        primaryContainer = purple700,
        background = graySurface,
        onBackground = Color(red = 230, green = 225, blue = 229),
        surface = graySurface,
        onSurface = Color(red = 230, green = 225, blue = 229),
    )
    MaterialTheme(if (darkTheme) darkColorScheme else lightColorScheme(), content = content)
}