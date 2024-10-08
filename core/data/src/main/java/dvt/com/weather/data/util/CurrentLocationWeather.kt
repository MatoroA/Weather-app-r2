package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.SharedFlow

interface CurrentLocationWeather {
    val location: SharedFlow<CurrentLocation?>
    val weather: SharedFlow<CurrentWeather?>

    suspend fun onLocationUpdate(location: CurrentLocation?)

    suspend fun weather(weather: CurrentWeather)
}