package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.SharedFlow

interface LocationTemperature {
    val location: SharedFlow<CurrentLocation?>
    val temperature: SharedFlow<Double>

    suspend fun onLocationUpdate(location: CurrentLocation?)

    suspend fun temperature(temperature: Double)
}