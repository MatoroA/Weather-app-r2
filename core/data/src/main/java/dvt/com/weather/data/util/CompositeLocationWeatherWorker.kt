package dvt.com.weather.data.util

import android.util.Log
import dvt.com.weather.common.di.ApplicationScope
import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.LocationCoordinates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class is responsible for enqueueing network call when location is available
 */

class CompositeLocationWeatherWorker @Inject constructor(
    private val liveLocationManager: LiveLocationManager,
    private val syncWeatherManager: SyncWeatherManager,
    @ApplicationScope private val applicationScope: CoroutineScope,
) {

    private companion object {
        val TAG = CompositeLocationWeatherWorker::class.simpleName
    }

    operator fun invoke() = applicationScope.launch {
        liveLocationManager.locationStatus.collectLatest { value: CurrentLocation? ->
            Log.d(TAG, "invoke: $value")
            value?.let {
                syncWeatherManager.startSyncWeatherManager(
                    coordinates = LocationCoordinates(
                        latitude = value.latitude,
                        longitude = value.longitude
                    )
                )
            }
        }
    }


}