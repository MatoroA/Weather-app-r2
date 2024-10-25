package dvt.com.weather.data.util

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.renderscript.RenderScript
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.tasks.CancellationTokenSource
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class LiveLocationManagerImpl @Inject constructor(
    private val fusedLocation: FusedLocationProviderClient,
    private val geocoder: Geocoder,
) : LiveLocationManager {

    companion object {
        val TAG = LiveLocationManagerImpl::class.simpleName
    }

    private val _location = MutableStateFlow<CurrentLocation?>(null)
    override val locationStatus: StateFlow<CurrentLocation?> = _location


    @SuppressLint("MissingPermission")
    override fun getLocation() {
        val mLocationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 60000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                Log.d(TAG, "onLocationResult: $locationResult")
                for (location in locationResult.locations) {
                    if (location != null) {
                        getLocationInformation(location)
                    }
                }
            }
        }
        fusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

    private fun getLocationInformation(location: Location) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1,
                geocodeListener
            )
        } else {
            val address =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    ?.firstOrNull()?.toCurrentLocation()
            onCurrentLocation(address)
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