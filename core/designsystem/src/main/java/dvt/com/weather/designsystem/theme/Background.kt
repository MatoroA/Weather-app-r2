package dvt.com.weather.designsystem.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import dvt.com.weather.designsystem.R
import dvt.com.weather.model.WeatherType


@Immutable
data class Background(
    val color: Color = Color.Unspecified,
    @DrawableRes val image: Int = R.drawable.forest_rainy,
    val weatherType: WeatherType = WeatherType.UNSPECIFIED,
)


val LocalBackgroundTheme = staticCompositionLocalOf { Background() }