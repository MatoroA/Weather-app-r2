package dvt.com.weather.data.util

import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.SharedFlow

interface LiveWeatherManager {

    val weather: SharedFlow<CurrentWeather>

    suspend fun liveWeather(weather: CurrentWeather)
}