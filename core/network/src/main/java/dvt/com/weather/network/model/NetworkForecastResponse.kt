package dvt.com.weather.network.model

import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.Weather
import kotlinx.serialization.Serializable

@Serializable
data class NetworkForecastResponse(
    val cod: Int,
    val list: List<NetworkForecast>,
)

//TODO: convert network model to DB entity
fun NetworkForecastResponse.toExternalForecastList(): List<Forecast> =
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