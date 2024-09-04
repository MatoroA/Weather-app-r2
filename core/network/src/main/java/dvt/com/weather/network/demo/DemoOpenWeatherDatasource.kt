package dvt.com.weather.network.demo

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.WeatherLocationParam
import dvt.com.weather.network.model.NetworkWeatherForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DemoOpenWeatherDatasource @Inject constructor(
    @ApplicationContext val context: Context,
) : WeatherDataSource {
    override suspend fun getWeather(location: WeatherLocationParam): Flow<NetworkWeatherForecast> {
        withContext(Dispatchers.IO) {
            context.assets.open(CURRENT_LOCATION).use {
                it.
            }
        }
    }


    companion object {
        const val CURRENT_LOCATION = "current_location.json"
    }
}