package dvt.com.weather.data.repository

import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(longitude: Double, latitude: Double): Flow<CurrentWeather>
    fun getWeatherForecast(longitude: Double, latitude: Double): Flow<List<Forecast>>
}