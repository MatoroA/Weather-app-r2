package dvt.com.weather.data.util

import dvt.com.weather.data.util.LocationStatus.*
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class LiveLocationManagerImpl @Inject constructor() : LiveLocationManager {

    private val _location = MutableSharedFlow<CurrentLocation?>()
    override val locationStatus: SharedFlow<CurrentLocation?> = _location

    override suspend fun onLocationUpdate(location: CurrentLocation?) {
        _location.emit(location)
    }
}