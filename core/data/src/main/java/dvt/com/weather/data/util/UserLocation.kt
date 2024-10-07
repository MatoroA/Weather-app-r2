package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UserLocation {

    val isFlow: Flow<Boolean>

    val location: SharedFlow<CurrentLocation?>

    suspend fun onLocationUpdate(location: CurrentLocation?)
}