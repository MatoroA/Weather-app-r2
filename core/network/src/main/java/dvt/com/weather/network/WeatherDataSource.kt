package dvt.com.weather.network

import dvt.com.weather.network.model.NetworkWeatherForecast
import kotlinx.coroutines.flow.Flow

/**
 * latitude, longitude parameters
 */
data class WeatherLocationParam(
    val longitude: Double,
    val latitude: Double,
)

interface WeatherDataSource {

    suspend fun getWeather(location: WeatherLocationParam): NetworkWeatherForecast
}
