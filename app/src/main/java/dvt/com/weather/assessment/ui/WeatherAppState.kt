package dvt.com.weather.assessment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import dvt.com.weather.data.util.CurrentLocationWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberWeatherAppState(
    currentLocationWeather: CurrentLocationWeather,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): WeatherAppState {
    return remember(currentLocationWeather, coroutineScope) {
        WeatherAppState(currentLocationWeather, coroutineScope)
    }
}

@Stable
class WeatherAppState(
    currentLocationWeather: CurrentLocationWeather,
    coroutineScope: CoroutineScope,
) {
    val currentTemperature = currentLocationWeather.weather.stateIn(
        scope = coroutineScope,
        initialValue = null,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}