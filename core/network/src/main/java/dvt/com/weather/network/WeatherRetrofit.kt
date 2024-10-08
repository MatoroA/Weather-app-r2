package dvt.com.weather.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dvt.com.weather.network.model.NetworkForecastResponse
import dvt.com.weather.network.model.NetworkWeatherForecast
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


interface RetrofitWeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String = "metric",
    ): NetworkWeatherForecast

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String = "metric",
    ): NetworkForecastResponse
}
