package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class UserCurrentLocationImpl @Inject constructor() : UserLocation {

    private val _location = MutableSharedFlow<CurrentLocation?>()
    override val location: SharedFlow<CurrentLocation?> = _location

    override suspend fun onLocationUpdate(location: CurrentLocation?) {
        _location.emit(location)
    }
}