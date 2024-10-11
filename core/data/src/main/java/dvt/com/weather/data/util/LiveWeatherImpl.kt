package dvt.com.weather.data.util

import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Weather
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class LiveWeatherImpl : LiveWeather {


    private val _weather = MutableSharedFlow<CurrentWeather>()
    override val weather: SharedFlow<CurrentWeather> = _weather

    override suspend fun liveWeather(weather: CurrentWeather) {
        _weather.emit(weather)
    }
}