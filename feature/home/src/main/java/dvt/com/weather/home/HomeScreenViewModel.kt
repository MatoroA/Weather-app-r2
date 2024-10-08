package dvt.com.weather.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.CurrentLocationWeather
import dvt.com.weather.domain.WeatherForecastUseCase
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
    currentLocationWeather: CurrentLocationWeather,
) : ViewModel() {

    companion object {
        val TAG: String = HomeScreenViewModel::class.java.simpleName
    }

    val currentWeather: StateFlow<HomeUiState> =
        currentLocationWeather.location.flatMapLatest { currentLocation ->
            Log.d(TAG, "get user current location: $currentLocation")
            when {
                currentLocation != null -> weatherForecastUseCase.invoke(0.0, 0.0)
                else -> flowOf(null)
            }
        }
            .map { results ->
                when {
                    results != null -> {
                        currentLocationWeather.weather(results.currentWeather)
                        HomeUiState.Success(
                            current = results.currentWeather,
                            forecasts = results.forecast
                        )
                    }

                    else -> HomeUiState.NotFound
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

    data class Success(val current: CurrentWeather, val forecasts: List<Forecast>) : HomeUiState
}