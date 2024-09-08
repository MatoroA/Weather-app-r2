package dvt.com.weather.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("temp")
    val temperature: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("temp_min")
    val temperatureMin: Double,
    @SerialName("temp_max")
    val temperatureMax: Double,
    val pressure: Int,
    val humidity: Int,
    @SerialName("sea_level")
    val seaLevel: Int,
    @SerialName("grnd_level")
    val groundLevel: Int,
)
