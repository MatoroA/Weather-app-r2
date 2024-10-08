package dvt.com.weather.assessment.ui

import androidx.compose.runtime.mutableStateOf
import dvt.com.weather.model.CurrentLocation


class WeatherAppState {
    private val location = mutableStateOf<CurrentLocation?>(null)

    fun updatedLocation(location: CurrentLocation) {

    }
}