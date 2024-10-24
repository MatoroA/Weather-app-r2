package dvt.com.weather.domain

import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.model.ForecastCurrentWeather
import dvt.com.weather.model.LocationCoordinates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

//class WeatherForecastUseCase @Inject constructor(
//    private val weatherRepository: WeatherRepository,
//) {
//
//    operator fun invoke(locationCoordinates: LocationCoordinates): Flow<ForecastCurrentWeather> =
//        combine(
//            weatherRepository.getWeatherForecast(locationCoordinates),
//            weatherRepository.getCurrentWeather(locationCoordinates)
//        ) { forecast, current ->
//
//            ForecastCurrentWeather(
//                forecast = forecast,
//                currentWeather = current
//            )
//        }
//}