package com.qxdzbc.pcr.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200,
    //==
    primary =  PcrBlue2,
    primaryVariant = PcrBlue2,
    onPrimary= Color.White,

    surface = DarkSurface,
    onSurface = Color.White,

    secondary = Color(0xff343a46),
    secondaryVariant = Color(0xff343a46),
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200
    //====
    primary =  PcrBlue2,
    onPrimary= Color.White,
    primaryVariant = PcrBlue2,

    secondary = Color(0xffe6f7fe),
    secondaryVariant = Color(0xffe6f7fe),
    onSecondary = Color(0xff277fa7),

    surface = PcrWhite,
    onSurface = Color(0xff23272f),

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PCRTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
