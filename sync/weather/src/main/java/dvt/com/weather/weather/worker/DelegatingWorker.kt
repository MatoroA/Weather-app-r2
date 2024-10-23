package dvt.com.weather.weather.worker

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import dvt.com.weather.model.LocationCoordinates
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass


@EntryPoint
@InstallIn(SingletonComponent::class)
interface HiltWorkerFactoryEntryPoint {
    fun hiltWorkerFactory(): HiltWorkerFactory
}


const val WORKER_CLASS_NAME = "RouterWorkerDelegateClassName"
const val LONGITUDE = "Longitude"
const val LATITUDE = "latitude"


fun KClass<out CoroutineWorker>.delegate(coordinates: LocationCoordinates): Data =
    Data.Builder()
        .putString(WORKER_CLASS_NAME, qualifiedName)
        .putDouble(LONGITUDE, coordinates.longitude)
        .putDouble(LATITUDE, coordinates.latitude)
        .putString(WORKER_CLASS_NAME, qualifiedName)
        .build()

class DelegateWork(
    private val appContext: Context,
    private val workerParameters: WorkerParameters,
) : CoroutineWorker(appContext, workerParameters) {

    private val workerClasName = workerParameters.inputData.getString(WORKER_CLASS_NAME) ?: ""

    private val delegateWorker =
        EntryPointAccessors.fromApplication<HiltWorkerFactoryEntryPoint>(appContext)
            .hiltWorkerFactory()
            .createWorker(appContext, workerClasName, workerParameters) as CoroutineWorker?
            ?: throw IllegalArgumentException("Worker not found")


    override suspend fun doWork(): Result = delegateWorker.doWork()
}