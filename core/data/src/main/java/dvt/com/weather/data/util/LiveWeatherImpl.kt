package dvt.com.weather.data.util

import dvt.com.weather.model.weather.Weather
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class LiveWeatherImpl : LiveWeather {


    private val _liveWeather = MutableSharedFlow<Weather>()
    override val liveWeather: SharedFlow<Weather> = _liveWeather

    override suspend fun liveWeather(weather: Weather) {
        _liveWeather.emit(weather)
    }
}