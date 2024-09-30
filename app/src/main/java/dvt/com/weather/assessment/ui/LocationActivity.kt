package dvt.com.weather.assessment.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import dvt.com.weather.assessment.hasPermissions
import dvt.com.weather.model.CurrentLocation
import javax.inject.Inject

@AndroidEntryPoint
abstract class LocationActivity : ComponentActivity() {

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @Inject
    lateinit var geocoder: Geocoder

    private companion object {
        val TAG: String = LocationActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notGrated = !hasPermissions(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (notGrated) {
            val resultLauncher =
                registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
                    val granted = results.values.all { it }
                    if (granted) {
                        getLocation()
                    } else {
                        onCurrentLocation(null)
                    }
                }

            resultLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (Build.VERSION.SDK_INT >= 33) {
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

    abstract fun onCurrentLocation(location: CurrentLocation?)

    private fun Address.toCurrentLocation() = CurrentLocation(
        city = adminArea,
        longitude = longitude,
        latitude = latitude,
    )
}