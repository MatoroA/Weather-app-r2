package dvt.com.weather.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeRoute(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val state by viewModel.weatherForecast.collectAsStateWithLifecycle()

    HomeScreen(state = state)
}

@Composable
internal fun HomeScreen(modifier: Modifier = Modifier, state: HomeUiState) {

    when (state) {
        HomeUiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is HomeUiState.Success -> Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Temperature is: ${state.forecast.main.temperature}")
        }
    }


}