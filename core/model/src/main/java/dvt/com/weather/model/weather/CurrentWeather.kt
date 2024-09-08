package dvt.com.weather.model.weather

data class CurrentWeather(
    val weather: List<Weather>,
    val main: Main,
)