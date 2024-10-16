package dvt.com.weather.home.permission

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.LiveLocationManager
import javax.inject.Inject

@HiltViewModel
class LocationPermissionViewModel @Inject constructor(
    private val liveLocationManager: LiveLocationManager,
) : ViewModel() {

    fun getLiveLocation() {
        liveLocationManager.getLocation()
    }
}

sealed interface LocationPermStatus {
    data object Denied : LocationPermStatus
    data object NotGranted : LocationPermStatus
    data object Granted : LocationPermStatus
}