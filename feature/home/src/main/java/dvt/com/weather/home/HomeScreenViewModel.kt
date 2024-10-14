package dvt.com.weather.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.DvtLocationManager
import dvt.com.weather.data.util.LiveWeather
import dvt.com.weather.data.util.LocationStatus
import dvt.com.weather.domain.WeatherForecastUseCase
import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Forecast
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    weatherForecastUseCase: WeatherForecastUseCase,
    dvtLocationManager: DvtLocationManager,
    liveWeather: LiveWeather,
) : ViewModel() {

    companion object {
        val TAG: String = HomeScreenViewModel::class.java.simpleName
    }

    val currentWeather: StateFlow<HomeUiState> =
        dvtLocationManager.locationStatus.flatMapLatest { status ->
            Log.d(TAG, "get user current location: $status")
            when (status) {
                LocationStatus.Denied -> flowOf(HomeUiState.PermDenied)
                LocationStatus.NotFound -> flowOf(HomeUiState.NotFound)
                is LocationStatus.Granted -> {
                    val location = status.location
                    weatherForecastUseCase(
                        location.latitude,
                        location.longitude
                    ).map { data ->
                        // update current user location weather
                        liveWeather.liveWeather(data.currentWeather)

                        HomeUiState.Success(
                            current = data.currentWeather,
                            forecasts = data.forecast,
                            location = location
                        )

                    }
                }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState.Loading
            )


}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object NotFound : HomeUiState
    data object PermDenied : HomeUiState

    data class Success(
        val current: CurrentWeather,
        val forecasts: List<Forecast>,
        val location: CurrentLocation,
    ) : HomeUiState
}