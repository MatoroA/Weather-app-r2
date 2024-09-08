package dvt.com.weather.domain

import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.model.ForecastCurrentWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class WeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) {

    operator fun invoke(latitude: Double, longitude: Double): Flow<ForecastCurrentWeather> =
        combine(
            weatherRepository.getWeatherForecast(longitude, latitude),
            weatherRepository.getCurrentWeather(longitude, latitude)
        ) { forecast, current ->

            ForecastCurrentWeather(
                forecast = forecast,
                currentWeather = current
            )
        }
}