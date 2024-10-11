package dvt.com.weather.data.util

import dvt.com.weather.model.weather.Weather
import kotlinx.coroutines.flow.SharedFlow

interface LiveWeather {

    val liveWeather: SharedFlow<Weather>

    suspend fun liveWeather(weather: Weather)
}