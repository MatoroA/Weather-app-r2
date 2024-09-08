package dvt.com.weather.network.model

import kotlinx.serialization.Serializable


@Serializable
data class NetworkForecast(
    val dt: Long,
    val main: NetworkMain,
    val weather: List<NetworkWeather>
)
