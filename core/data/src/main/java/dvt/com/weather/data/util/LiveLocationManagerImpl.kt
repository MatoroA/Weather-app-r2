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
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class LiveLocationManagerImpl @Inject constructor(
    private val fusedLocation: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    @DvtDispatcher(DvtDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : LiveLocationManager {

    companion object {
        val TAG = LiveLocationManagerImpl::class.simpleName
    }

    private val _location = MutableSharedFlow<CurrentLocation?>()
    override val locationStatus: SharedFlow<CurrentLocation?> = _location

    override suspend fun onLocationUpdate(location: CurrentLocation?) {
        _location.emit(location)
    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
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
        val successful = _location.tryEmit(location)
        Log.d(TAG, "onCurrentLocation: did it emit location successfully? $successful")
    }

    private fun Address.toCurrentLocation() = CurrentLocation(
        city = adminArea,
        longitude = longitude,
        latitude = latitude,
    )


}