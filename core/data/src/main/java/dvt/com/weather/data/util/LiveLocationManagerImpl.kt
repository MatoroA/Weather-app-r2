package dvt.com.weather.data.util

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import dvt.com.weather.common.DvtDispatcher
import dvt.com.weather.common.DvtDispatchers
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LiveLocationManagerImpl @Inject constructor(
    private val fusedLocation: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    @DvtDispatcher(DvtDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : LiveLocationManager {

    companion object {
        val TAG = LiveLocationManagerImpl::class.simpleName
    }

    private val _location = MutableStateFlow<CurrentLocation?>(null)
    override val locationStatus: StateFlow<CurrentLocation?> = _location


    @SuppressLint("MissingPermission")
    override fun getLocation() {
        fusedLocation.lastLocation.addOnSuccessListener { location ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(location.latitude, location.longitude, 1, geocodeListener)
            } else {
                val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    ?.firstOrNull()?.toCurrentLocation()
                onCurrentLocation(address)
            }
        }
    }

    private val geocodeListener = Geocoder.GeocodeListener { addresses ->
        onCurrentLocation(addresses.firstOrNull()?.toCurrentLocation())
    }

    private fun onCurrentLocation(location: CurrentLocation?) {
        _location.value = location
    }

    private fun Address.toCurrentLocation(): CurrentLocation {
        Log.d(TAG, "toCurrentLocation: longitude: $longitude, latitude: $latitude")
        return CurrentLocation(
            city = adminArea,
            longitude = longitude,
            latitude = latitude,
        )
    }
}