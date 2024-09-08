package dvt.com.weather.model.weather

data class Forecast(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
)