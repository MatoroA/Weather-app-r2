package dvt.com.weather.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.LiveLocationManager
import dvt.com.weather.data.util.LiveWeatherManager
import dvt.com.weather.domain.WeatherForecastUseCase
import dvt.com.weather.home.HomeUiState.*
import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Forecast
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    weatherForecastUseCase: WeatherForecastUseCase,
    liveLocationManager: LiveLocationManager,
    liveWeatherManager: LiveWeatherManager,
) : ViewModel() {

    companion object {
        val TAG: String = HomeScreenViewModel::class.java.simpleName
    }

    val currentWeather: StateFlow<HomeUiState> =
        liveLocationManager.locationStatus.flatMapLatest { location ->
            Log.d(TAG, "get user current location: $location")
            when (location) {
                null -> flowOf(Loading)
                else -> {
                    weatherForecastUseCase(
                        longitude = location.longitude,
                        latitude = location.latitude
                    ).map { userLocationWeather ->
                        liveWeatherManager.liveWeather(weather = userLocationWeather.currentWeather)

                        Success(
                            current = userLocationWeather.currentWeather,
                            forecasts = userLocationWeather.forecast,
                            location = location
                        )
                    }
                }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = Loading
            )


}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object NotFound : HomeUiState

    data class Success(
        val current: CurrentWeather,
        val forecasts: List<Forecast>,
        val location: CurrentLocation,
    ) : HomeUiState
}