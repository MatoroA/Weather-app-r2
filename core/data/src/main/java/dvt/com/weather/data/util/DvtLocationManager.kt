package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.SharedFlow

interface DvtLocationManager {
    val locationStatus: SharedFlow<LocationStatus>

    suspend fun onLocationUpdate(location: CurrentLocation)

    suspend fun permissionDenied()

    suspend fun notFound()
}


sealed interface LocationStatus {
    data class Granted(
        val location: CurrentLocation,
    ) : LocationStatus

    data object Denied : LocationStatus
    data object NotFound : LocationStatus
}