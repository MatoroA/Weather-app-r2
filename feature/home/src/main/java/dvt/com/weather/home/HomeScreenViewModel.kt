package dvt.com.weather.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.domain.WeatherForecastUseCase
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Forecast
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    weatherForecastUseCase: WeatherForecastUseCase,
) : ViewModel() {

    val weatherForecast = weatherForecastUseCase(latitude = 0.0, longitude = 0.0)
        .map {
            HomeUiState.Success(
                current = it.currentWeather,
                forecasts = it.forecast
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )

}

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(val current: CurrentWeather, val forecasts: List<Forecast>) : HomeUiState
}