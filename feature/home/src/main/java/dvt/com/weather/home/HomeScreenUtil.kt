package dvt.com.weather.home

import java.text.SimpleDateFormat
import java.util.Locale

fun getDayOfWeek(timestamp: Long): String {
    return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000)
}