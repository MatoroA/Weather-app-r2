package dvt.com.weather.data.repository

import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(longitude: Double, latitude: Double): Flow<WeatherForecast>
    fun getCurrentWeatherForecast(longitude: Double, latitude: Double): Flow<List<Forecast>>
}