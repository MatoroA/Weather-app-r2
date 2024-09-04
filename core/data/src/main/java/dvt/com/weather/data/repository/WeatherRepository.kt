package dvt.com.weather.data.repository

import dvt.com.weather.model.weather.WeatherForecast
import dvt.com.weather.network.WeatherLocationParam
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(locationParam: WeatherLocationParam): Flow<WeatherForecast>
}