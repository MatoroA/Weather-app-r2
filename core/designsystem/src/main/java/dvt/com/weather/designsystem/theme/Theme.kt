package dvt.com.weather.designsystem.theme

import androidx.compose.ui.graphics.Color
import dvt.com.weather.designsystem.R


fun weatherTheme(
    sunny: Boolean = false,
    cloudy: Boolean = false,
    rainy: Boolean = false,
) {

    val background = when {
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

}