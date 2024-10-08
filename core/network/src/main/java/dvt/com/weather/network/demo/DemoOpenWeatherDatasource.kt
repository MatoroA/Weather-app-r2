//package dvt.com.weather.network.demo
//
//import android.content.Context
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dvt.com.weather.network.WeatherDataSource
//import dvt.com.weather.network.model.NetworkForecastResponse
//import dvt.com.weather.network.model.NetworkWeatherForecast
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import kotlinx.serialization.ExperimentalSerializationApi
//import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.decodeFromStream
//import javax.inject.Inject
//
//class DemoOpenWeatherDatasource @Inject constructor(
//    @ApplicationContext val context: Context,
//    private val json: Json,
//) : WeatherDataSource {
//    @OptIn(ExperimentalSerializationApi::class)
//    override suspend fun getWeather(longitude: Double, latitude: Double): NetworkWeatherForecast =
//        withContext(Dispatchers.IO) {
//            context.assets.open(CURRENT_LOCATION).use(json::decodeFromStream)
//        }
//
//    @OptIn(ExperimentalSerializationApi::class)
//    override suspend fun getForecast(longitude: Double, latitude: Double): NetworkForecastResponse =
//        withContext(Dispatchers.IO) {
//            context.assets.open(FORECAST).use(json::decodeFromStream)
//        }
//
//    companion object {
//        const val CURRENT_LOCATION = "current_location.json"
//        const val FORECAST = "five_days_forecast.json"
//    }
//}