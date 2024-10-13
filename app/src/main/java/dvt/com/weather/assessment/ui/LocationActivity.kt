package dvt.com.weather.assessment.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import dvt.com.weather.assessment.hasPermissions
import dvt.com.weather.data.util.DvtLocationManager
import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.launch
import javax.inject.Inject


// This is an old way of handling permissions requests
@AndroidEntryPoint
abstract class LocationActivity : ComponentActivity() {

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @Inject
    lateinit var geocoder: Geocoder

    @Inject
    lateinit var dvtLocationManager: DvtLocationManager

    private companion object {
        val TAG: String = LocationActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notGrated = !hasPermissions(*locationPermissions())

        if (notGrated) {
            requestLocationPermissions()
        } else {
            getLocation()
        }
    }

    fun requestLocationPermissions() {
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
                val granted = results.values.all { it }
                if (granted) {
                    getLocation()
                } else {
                    runsSuspendMethod {
                        dvtLocationManager.permissionDenied()
                    }
                }
            }

        resultLauncher.launch(locationPermissions())
    }

    private fun locationPermissions(): Array<String> = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
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
        runsSuspendMethod {
            if (location == null) {
                dvtLocationManager.notFound()
            } else {
                dvtLocationManager.onLocationUpdate(location)
            }
        }
    }

    private fun runsSuspendMethod(blocking: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                blocking()
            }
        }
    }

    private fun Address.toCurrentLocation() = CurrentLocation(
        city = adminArea,
        longitude = longitude,
        latitude = latitude,
    )
}