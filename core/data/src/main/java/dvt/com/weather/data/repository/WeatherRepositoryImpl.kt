package dvt.com.weather.data.repository

import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.Weather
import dvt.com.weather.model.weather.WeatherForecast
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.model.NetworkWeatherForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherDataSource,
) : WeatherRepository {
    override fun getWeatherForecast(longitude: Double, latitude: Double): Flow<WeatherForecast> =
        flow {
            emit(
                dataSource.getWeather(longitude, latitude).toExternalModel()
            )
        }

    private fun NetworkWeatherForecast.toExternalModel(): WeatherForecast {
        return WeatherForecast(
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
                seaLeve = main.seaLeve,
                groundLevel = main.groundLevel,
                feelsLike = main.feelsLike,
            )
        )
    }

}