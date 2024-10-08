package dvt.com.weather.data.util

import dvt.com.weather.model.CurrentLocation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class LocationTemperatureImpl @Inject constructor() : LocationTemperature {

    private val _location = MutableSharedFlow<CurrentLocation?>()
    override val location: SharedFlow<CurrentLocation?> = _location

    private val _temperature = MutableSharedFlow<Double>()
    override val temperature: SharedFlow<Double> = _temperature

    override suspend fun onLocationUpdate(location: CurrentLocation?) {
        _location.emit(location)
    }

    override suspend fun temperature(temperature: Double) {
        _temperature.emit(temperature)
    }


}