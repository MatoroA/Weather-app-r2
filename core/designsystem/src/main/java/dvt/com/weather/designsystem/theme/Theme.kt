package dvt.com.weather.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import dvt.com.weather.designsystem.R
import dvt.com.weather.model.WeatherType


@Composable
fun WeatherTheme(
    theme: WeatherType,
    content: @Composable () -> Unit,
) {

    val backgroundTheme = when (theme) {
        WeatherType.SUNNY -> Background(
            color = sunnyColor,
            image = R.drawable.forest_sunny,
            theme = dvt.com.weather.model.WeatherType.SUNNY,
        )

        WeatherType.CLOUDY -> Background(
            color = cloudyColor,
            image = R.drawable.forest_cloudy,
            theme = dvt.com.weather.model.WeatherType.CLOUDY,
        )

        WeatherType.RAINY -> Background(
            color = rainyColor,
            image = R.drawable.forest_rainy,
            theme = dvt.com.weather.model.WeatherType.RAINY,
        )

        else -> Background(
            color = Color.Unspecified,
            image = R.drawable.forest_rainy,
            theme = dvt.com.weather.model.WeatherType.UNSPECIFIED,
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

