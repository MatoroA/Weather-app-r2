package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class DvtLocationManagerImpl @Inject constructor() : DvtLocationManager {

    private val _location = MutableSharedFlow<LocationStatus>()
    override val locationStatus: SharedFlow<LocationStatus> = _location

    //    private val _weather = MutableSharedFlow<CurrentWeather?>()
//    override val weather: SharedFlow<CurrentWeather?> = _weather
//
    override suspend fun onLocationUpdate(location: CurrentLocation) {
        _location.emit(LocationStatus.Granted(location))
    }

    override suspend fun permissionDenied() {
        _location.emit(LocationStatus.Denied)
    }
//
//    override suspend fun weather(weather: CurrentWeather) {
//        _weather.emit(weather)
//    }


}