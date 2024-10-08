package dvt.com.weather.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import dvt.com.weather.designsystem.R
import dvt.com.weather.model.WeatherType


@Composable
fun WeatherTheme(
    weatherType: WeatherType = WeatherType.SUNNY,
    content: @Composable () -> Unit,
) {

    val backgroundTheme = when (weatherType) {
        WeatherType.SUNNY -> Background(
            color = sunnyColor,
            image = R.drawable.forest_sunny,
            weatherType = WeatherType.SUNNY,
        )

        WeatherType.CLOUDY -> Background(
            color = cloudyColor,
            image = R.drawable.forest_cloudy,
            weatherType = WeatherType.CLOUDY,
        )

        WeatherType.RAINY -> Background(
            color = rainyColor,
            image = R.drawable.forest_rainy,
            weatherType = WeatherType.RAINY,
        )

        else -> Background(
            color = Color.Unspecified,
            image = R.drawable.forest_rainy,
            weatherType = WeatherType.UNSPECIFIED,
        )
    }

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme
    ) {
        MaterialTheme {
            content()
        }
    }
}

