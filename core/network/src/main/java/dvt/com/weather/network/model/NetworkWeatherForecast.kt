package dvt.com.weather.network.model

import kotlinx.serialization.Serializable


@Serializable
data class NetworkWeatherForecast(
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
)

