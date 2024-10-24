package dvt.com.weather.weather.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.data.util.Syncable
import dvt.com.weather.data.util.Synchronizer
import dvt.com.weather.model.LocationCoordinates
import dvt.com.weather.weather.helpers.WeatherWorkerUtil.WeatherConstraint
import kotlinx.coroutines.delay

@HiltWorker
class WeatherWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val weatherRepository: WeatherRepository,
) : CoroutineWorker(context, workerParameters), Synchronizer {

    override suspend fun doWork(): Result {

        val latitude = workerParameters.inputData.getDouble(LATITUDE, 0.0)
        val longitude = workerParameters.inputData.getDouble(LONGITUDE, 0.0)

        Log.d(TAG, "doWork: longitude => $longitude, latitude => $latitude")

        val syncSuccessful = weatherRepository.sync(
            LocationCoordinates(
                latitude = latitude,
                longitude = longitude
            )
        )

        return if (syncSuccessful) {
            Result.success()
        } else {
            Result.failure()
        }
    }


    companion object {
        fun startWeatherSyncWork(coordinates: LocationCoordinates): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<DelegateWork>()
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setInputData(WeatherWorker::class.delegate(coordinates))
                .setConstraints(WeatherConstraint)
                .build()
    }

}

const val TAG = "WeatherWorkerClass"