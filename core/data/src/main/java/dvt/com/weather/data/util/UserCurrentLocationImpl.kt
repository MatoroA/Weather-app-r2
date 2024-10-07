package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class UserCurrentLocationImpl @Inject constructor() : UserLocation {

    private val _location = MutableSharedFlow<CurrentLocation?>()
    override val location: SharedFlow<CurrentLocation?> = _location

    private val isBoolean = MutableStateFlow<Boolean>(true)
    override val isFlow: Flow<Boolean>
        get() = isBoolean

    override suspend fun onLocationUpdate(location: CurrentLocation?) {
        isBoolean.value = !isBoolean.value
        _location.emit(location)
    }
}