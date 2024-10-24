package dvt.com.weather.data.repository

import android.util.Log
import dvt.com.weather.common.DvtDispatcher
import dvt.com.weather.common.DvtDispatchers
import dvt.com.weather.data.util.Synchronizer
import dvt.com.weather.data.util.remoteNetworkCall
import dvt.com.weather.model.LocationCoordinates
import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.Weather
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.model.NetworkForecastResponse
import dvt.com.weather.network.model.NetworkWeatherForecast
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


    private fun NetworkWeatherForecast.toExternalModel(): CurrentWeather {
        return CurrentWeather(
            weather = this.weather.map {
                Weather(
                    id = it.id,
                    main = it.main,
                    icon = it.icon,
                    description = it.description,
                )
            },
            main = Main(
                temperatureMax = this.main.temperatureMax,
                temperatureMin = main.temperatureMin,
                temperature = main.temperature,
                pressure = main.pressure,
                humidity = main.humidity,
                seaLevel = main.seaLevel,
                groundLevel = main.groundLevel,
                feelsLike = main.feelsLike,
            )
        )
    }


    private fun NetworkForecastResponse.toExternalForecastList(): List<Forecast> =
        this.list.map {
            Forecast(
                dt = it.dt,
                main = Main(
                    temperatureMax = it.main.temperatureMax,
                    temperatureMin = it.main.temperatureMin,
                    temperature = it.main.temperature,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity,
                    seaLevel = it.main.seaLevel,
                    groundLevel = it.main.groundLevel,
                    feelsLike = it.main.feelsLike,
                ),
                weather = it.weather.map { weather ->
                    Weather(
                        id = weather.id,
                        main = weather.main,
                        icon = weather.icon,
                        description = weather.description,
                    )
                }
            )
        }

}