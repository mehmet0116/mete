package com.metegelistirme.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4FC3F7),
    secondary = Color(0xFFFFB74D),
    tertiary = Color(0xFFFF4081),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF5F5F5)
)

@Composable
fun MeteEgitimUygulamasiTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}

