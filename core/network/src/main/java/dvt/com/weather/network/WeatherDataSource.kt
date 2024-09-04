package dvt.com.weather.network

import dvt.com.weather.network.model.NetworkWeatherForecast

interface WeatherDataSource {

    suspend fun getWeather(longitude: Double, latitude: Double): NetworkWeatherForecast
}
