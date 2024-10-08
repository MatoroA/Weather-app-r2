package dvt.com.weather.network

import dvt.com.weather.network.model.NetworkForecastResponse
import dvt.com.weather.network.model.NetworkWeatherForecast
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(
    retrofit: Retrofit,
) : WeatherDataSource {
    private val weatherApi = retrofit.create(RetrofitWeatherApi::class.java)
    override suspend fun getWeather(longitude: Double, latitude: Double): NetworkWeatherForecast =
        weatherApi.getCurrentWeather(latitude, longitude)

    override suspend fun getForecast(longitude: Double, latitude: Double): NetworkForecastResponse =
        weatherApi.getForecast(latitude, longitude)
}