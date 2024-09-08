package dvt.com.weather.network.model

import kotlinx.serialization.Serializable


@Serializable
data class NetworkWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)