package dvt.com.weather.assessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.CurrentLocationWeather
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val currentLocationWeather: CurrentLocationWeather,
) : ViewModel() {

    fun onUpdateLocation(currentLocation: CurrentLocation?) {
        viewModelScope.launch {
            currentLocationWeather.onLocationUpdate(currentLocation)
        }
    }
}