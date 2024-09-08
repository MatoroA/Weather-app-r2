package dvt.com.weather.data.repository

import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.Weather
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.model.NetworkForecastResponse
import dvt.com.weather.network.model.NetworkWeatherForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherDataSource,
) : WeatherRepository {
    override fun getCurrentWeather(longitude: Double, latitude: Double): Flow<CurrentWeather> =
        flow {
            emit(
                dataSource.getWeather(longitude, latitude).toExternalModel()
            )
        }

    override fun getWeatherForecast(
        longitude: Double,
        latitude: Double,
    ): Flow<List<Forecast>> = flow {
        emit(
            dataSource.getForecast(longitude, latitude)
                .toExternalForecastList()
        )

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