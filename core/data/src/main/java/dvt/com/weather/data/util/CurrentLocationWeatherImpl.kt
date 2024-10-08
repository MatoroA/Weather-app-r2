package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class CurrentLocationWeatherImpl @Inject constructor() : CurrentLocationWeather {

    private val _location = MutableSharedFlow<CurrentLocation?>()
    override val location: SharedFlow<CurrentLocation?> = _location

    private val _weather = MutableSharedFlow<CurrentWeather?>()
    override val weather: SharedFlow<CurrentWeather?> = _weather

    override suspend fun onLocationUpdate(location: CurrentLocation?) {
        _location.emit(location)
    }

    override suspend fun weather(weather: CurrentWeather) {
        _weather.emit(weather)
    }


}