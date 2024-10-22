package dvt.com.weather.data.repository

import dvt.com.weather.model.LocationCoordinates
import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    fun getCurrentWeather(coordinates: LocationCoordinates): Flow<CurrentWeather>

    fun getWeatherForecast(coordinates: LocationCoordinates): Flow<List<Forecast>>

    suspend fun sync(coordinates: LocationCoordinates): Boolean
}