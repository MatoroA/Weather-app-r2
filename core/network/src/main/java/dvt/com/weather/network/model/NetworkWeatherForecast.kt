package dvt.com.weather.network.model

import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.Weather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetworkWeatherForecast(
    val weather: List<NetworkWeather>,
    val main: NetworkMain,
    val visibility: Int,
    @SerialName("dt")
    val dateLong: Long
)

//TODO: convert network model to DB entity
fun NetworkWeatherForecast.toExternalModel(): CurrentWeather {
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