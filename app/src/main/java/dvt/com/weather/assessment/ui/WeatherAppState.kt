package dvt.com.weather.assessment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import dvt.com.weather.data.util.DvtLocationManager
import dvt.com.weather.data.util.LiveWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberWeatherAppState(
    liveWeather: LiveWeather,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): WeatherAppState {
    return remember(liveWeather, coroutineScope) {
        WeatherAppState(liveWeather, coroutineScope)
    }
}

@Stable
class WeatherAppState(
    liveWeather: LiveWeather,
    coroutineScope: CoroutineScope,
) {
    val currentTemperature = liveWeather.weather.stateIn(
        scope = coroutineScope,
        initialValue = null,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}