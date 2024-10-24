package dvt.com.weather.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.home.HomeUiState.*
import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Forecast
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    companion object {
        val TAG: String = HomeScreenViewModel::class.java.simpleName
    }

    val currentWeather: StateFlow<HomeUiState> =
        weatherRepository.mockDbCurrentWeather.flatMapLatest {
            when (it) {
                null -> flowOf(NotFound)
                else -> flowOf(
                    Success(
                        current = it.first,
                        forecasts = it.second
                    )
                )
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
    ) : HomeUiState
}