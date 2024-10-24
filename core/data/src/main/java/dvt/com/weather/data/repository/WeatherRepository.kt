package dvt.com.weather.data.repository

import dvt.com.weather.data.util.Syncable
import dvt.com.weather.data.util.Synchronizer
import dvt.com.weather.data.util.runSuspendCatch
import dvt.com.weather.model.LocationCoordinates
import dvt.com.weather.model.weather.Forecast
import dvt.com.weather.model.weather.CurrentWeather
import kotlinx.coroutines.flow.Flow


interface WeatherRepository : Syncable {
    // TODO: To be replaced once DB is integrated
    val mockDbCurrentWeather: Flow<Pair<CurrentWeather, List<Forecast>>?>

    fun getCurrentWeather(coordinates: LocationCoordinates): Flow<CurrentWeather>

    fun getWeatherForecast(coordinates: LocationCoordinates): Flow<List<Forecast>>

}