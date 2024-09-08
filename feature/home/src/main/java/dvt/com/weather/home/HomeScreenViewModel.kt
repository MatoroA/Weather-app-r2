package dvt.com.weather.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.model.weather.WeatherForecast
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    val weatherForecast = weatherRepository.getWeatherForecast(
        0.0,
        0.0
    ).map(HomeUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )

}

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(val forecast: WeatherForecast) : HomeUiState
}