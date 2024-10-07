package dvt.com.weather.assessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.UserLocation
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userLocation: UserLocation,
) : ViewModel() {

    fun onUpdateLocation(currentLocation: CurrentLocation?) {
        viewModelScope.launch {
            userLocation.onLocationUpdate(currentLocation)
        }
    }
}