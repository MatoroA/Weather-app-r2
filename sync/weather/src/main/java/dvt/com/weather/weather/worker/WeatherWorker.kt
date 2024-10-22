package dvt.com.weather.weather.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.weather.helpers.WeatherWorkerUtil.WeatherConstraint

@HiltWorker
class WeatherWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val weatherRepository: WeatherRepository,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {

        val successful = weatherRepository.sync()

        return if (successful) {
            Result.success()
        } else {
            Result.failure()
        }
    }


    companion object {
        fun startWeatherSyncWork() = OneTimeWorkRequestBuilder<DelegateWork>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(WeatherWorker::class.delegate())
            .setConstraints(WeatherConstraint)
            .build()
    }

}