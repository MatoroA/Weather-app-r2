package dvt.com.weather.assessment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import dvt.com.weather.data.util.LiveWeatherManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberWeatherAppState(
    liveWeatherManager: LiveWeatherManager,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): WeatherAppState {
    return remember(liveWeatherManager, coroutineScope) {
        WeatherAppState(liveWeatherManager, coroutineScope)
    }
}

@Stable
class WeatherAppState(
    liveWeatherManager: LiveWeatherManager,
    coroutineScope: CoroutineScope,
) {
    val currentTemperature = liveWeatherManager.weather.stateIn(
        scope = coroutineScope,
        initialValue = null,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}