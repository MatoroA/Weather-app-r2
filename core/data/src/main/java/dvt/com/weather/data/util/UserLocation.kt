package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.SharedFlow

interface UserLocation {

    val location: SharedFlow<CurrentLocation?>

    suspend fun onLocationUpdate(location: CurrentLocation?)
}