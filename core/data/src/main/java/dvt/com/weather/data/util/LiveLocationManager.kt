package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.SharedFlow

interface LiveLocationManager {
    val locationStatus: SharedFlow<CurrentLocation?>

    suspend fun onLocationUpdate(location: CurrentLocation?)
}

