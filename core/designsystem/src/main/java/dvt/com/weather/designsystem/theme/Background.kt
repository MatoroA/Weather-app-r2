package dvt.com.weather.designsystem.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import dvt.com.weather.designsystem.R

@Immutable
data class Background(
    val color: Color = Color.Unspecified,
    @DrawableRes val image: Int = R.drawable.forest_rainy,
)


val LocalBackgroundTheme = compositionLocalOf { Background() }