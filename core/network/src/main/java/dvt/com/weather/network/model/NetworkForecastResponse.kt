package dvt.com.weather.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkForecastResponse(
    val cod: Int,
    val list: List<NetworkForecast>,
)
