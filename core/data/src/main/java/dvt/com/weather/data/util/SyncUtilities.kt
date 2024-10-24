package dvt.com.weather.data.util

import android.util.Log
import dvt.com.weather.model.LocationCoordinates

interface Synchronizer {
    suspend fun Syncable.sync(coordinates: LocationCoordinates): Boolean =
        this@sync.syncWith(this@Synchronizer, coordinates)
}

interface Syncable {
    suspend fun syncWith(synchronizer: Synchronizer, coordinates: LocationCoordinates): Boolean
}

suspend fun Synchronizer.remoteNetworkCall(
    block: suspend () -> Unit,
): Boolean = runSuspendCatch {
    block()
}.isSuccess


suspend fun <T> runSuspendCatch(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (e: Exception) {
    Log.d(TAG, "runSuspendCatch: ${e.stackTrace}")
    Result.failure(e)
}

const val TAG = "RunSuspendCatch"