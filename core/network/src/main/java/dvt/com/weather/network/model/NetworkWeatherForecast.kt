package dvt.com.weather.network.model

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

