package dvt.com.weather.weather.status

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dvt.com.weather.data.util.SyncWeatherManager
import dvt.com.weather.model.LocationCoordinates
import dvt.com.weather.weather.worker.WeatherWorker
import javax.inject.Inject

class SyncWeatherManagerImpl @Inject constructor(
    @ApplicationContext val context: Context,
) : SyncWeatherManager {
    override fun startSyncWeatherManager(coordinates: LocationCoordinates) {
        WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                SYNC_WEATHER_MANAGER,
                ExistingWorkPolicy.REPLACE,
                WeatherWorker.startWeatherSyncWork(coordinates),
            )
        }
    }

    companion object {
        const val SYNC_WEATHER_MANAGER = "SyncWeatherManager"
    }
}