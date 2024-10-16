package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface LiveLocationManager {
    val locationStatus: StateFlow<CurrentLocation?>

    fun getLocation()
}

