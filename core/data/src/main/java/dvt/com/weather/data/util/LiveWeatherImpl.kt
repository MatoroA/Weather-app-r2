package dvt.com.weather.data.util

import dvt.com.weather.common.DvtDispatcher
import dvt.com.weather.common.DvtDispatchers.IO
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LiveWeatherImpl @Inject constructor(
//     private val dispatcherIO: CoroutineDispatcher,
) : LiveWeather {
    private val _weather = MutableSharedFlow<CurrentWeather>()
    override val weather: SharedFlow<CurrentWeather> = _weather

    override suspend fun liveWeather(weather: CurrentWeather) {
//        withContext(dispatcherIO) {
            _weather.emit(weather)
//        }
    }
}