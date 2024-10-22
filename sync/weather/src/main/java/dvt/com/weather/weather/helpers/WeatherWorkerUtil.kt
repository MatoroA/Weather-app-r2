package dvt.com.weather.weather.helpers

import androidx.work.Constraints
import androidx.work.NetworkType

object WeatherWorkerUtil {

    val WeatherConstraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
}