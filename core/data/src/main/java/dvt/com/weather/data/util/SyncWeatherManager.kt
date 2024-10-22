package dvt.com.weather.data.util

import dvt.com.weather.model.LocationCoordinates

interface SyncWeatherManager {

    fun startSyncWeatherManager(coordinates: LocationCoordinates)
}