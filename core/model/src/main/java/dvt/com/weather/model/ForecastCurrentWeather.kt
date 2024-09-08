package dvt.com.weather.model

import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Forecast

data class ForecastCurrentWeather(
    val forecast: List<Forecast>,
    val currentWeather: CurrentWeather,
)