package dvt.com.weather.assessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.LocationTemperature
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val locationTemperature: LocationTemperature,
) : ViewModel() {

    fun onUpdateLocation(currentLocation: CurrentLocation?) {
        viewModelScope.launch {
            locationTemperature.onLocationUpdate(currentLocation)
        }
    }
}