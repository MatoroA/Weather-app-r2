package dvt.com.weather.data.util

import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class LiveWeatherImpl @Inject constructor() : LiveWeather {
    private val _weather = MutableSharedFlow<CurrentWeather>()
    override val weather: SharedFlow<CurrentWeather> = _weather

    override suspend fun liveWeather(weather: CurrentWeather) {
        _weather.emit(weather)
    }
}