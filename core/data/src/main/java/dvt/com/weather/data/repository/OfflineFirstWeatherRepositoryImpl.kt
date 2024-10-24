package dvt.com.weather.data.repository

import android.util.Log
import dvt.com.weather.common.DvtDispatcher
import dvt.com.weather.common.DvtDispatchers
import dvt.com.weather.data.util.Synchronizer
import dvt.com.weather.data.util.remoteNetworkCall
import dvt.com.weather.model.LocationCoordinates
import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.model.NetworkForecastResponse
import dvt.com.weather.network.model.NetworkWeatherForecast
import dvt.com.weather.network.model.toExternalForecastList
import dvt.com.weather.network.model.toExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstWeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherDataSource,
    @DvtDispatcher(DvtDispatchers.IO) val ioDispatcher: CoroutineDispatcher,
) : WeatherRepository {

    companion object {
        private val TAG = OfflineFirstWeatherRepositoryImpl::class.simpleName
    }


    private val _currentWeather = MutableStateFlow<Pair<CurrentWeather, List<Forecast>>?>(null)
    override val mockDbCurrentWeather: Flow<Pair<CurrentWeather, List<Forecast>>?>
        get() = _currentWeather

    override fun getCurrentWeather(coordinates: LocationCoordinates): Flow<CurrentWeather> =
        flow {
            emit(
                dataSource.getWeather(coordinates.longitude, coordinates.latitude).toExternalModel()
            )
        }

    override fun getWeatherForecast(
        coordinates: LocationCoordinates,
    ): Flow<List<Forecast>> = flow {
        emit(
            dataSource.getForecast(coordinates.longitude, coordinates.latitude)
                .toExternalForecastList()
        )

    }

    override suspend fun syncWith(
        synchronizer: Synchronizer,
        coordinates: LocationCoordinates,
    ): Boolean = synchronizer.remoteNetworkCall {
        val (weather, forecast) = withContext(ioDispatcher) {
            val weatherResponse =
                async { dataSource.getWeather(coordinates.longitude, coordinates.latitude) }
            val forecastResponse =
                async { dataSource.getForecast(coordinates.longitude, coordinates.latitude) }

            return@withContext awaitAll(weatherResponse, forecastResponse).let {
                Pair(it.first() as NetworkWeatherForecast, it.last() as NetworkForecastResponse)
            }
        }

        //TODO: Update database here
    }

}