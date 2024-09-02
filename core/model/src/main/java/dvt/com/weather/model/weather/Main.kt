package dvt.com.weather.model.weather

data class Main(
    val temperature: Double,
    val feelsLike: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLeve: Int,
    val groundLevel: Int,
)