package dvt.com.weather.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import dvt.com.weather.designsystem.R


@Composable
fun WeatherTheme(
    sunny: Boolean = false,
    cloudy: Boolean = false,
    rainy: Boolean = false,
    content: @Composable () -> Unit,
) {

    val backgroundTheme = when {
        sunny -> Background(
            color = sunnyColor,
            image = R.drawable.forest_sunny
        )

        cloudy -> Background(
            color = cloudyColor,
            image = R.drawable.forest_cloudy
        )

        rainy -> Background(
            color = rainyColor,
            image = R.drawable.forest_rainy
        )

        else -> Background(
            color = Color.Unspecified,
            image = R.drawable.forest_rainy
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

