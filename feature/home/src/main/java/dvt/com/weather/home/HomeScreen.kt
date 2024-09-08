package dvt.com.weather.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dvt.com.weather.designsystem.theme.LocalBackgroundTheme
import dvt.com.weather.designsystem.theme.WeatherTheme
import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.WeatherForecast


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

        is HomeUiState.Success -> HomeWeather(state = state)
    }
}

@Composable
fun HomeWeather(modifier: Modifier = Modifier, state: HomeUiState.Success) {
    val current = LocalBackgroundTheme.current

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column {
            Image(
                painter = painterResource(id = current.image),
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "sunny"
            )
            Text(text = "Temperature is: ${state.forecast.main.temperature}")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeWeatherPreview() {
    WeatherTheme {
        HomeWeather(
            state = HomeUiState.Success(
                forecast = WeatherForecast(
                    weather = emptyList(),
                    main = Main(
                        temperatureMin = 0.0,
                        temperatureMax = 0.0,
                        temperature = 0.0,
                        humidity = 0,
                        pressure = 0,
                        groundLevel = 0,
                        seaLevel = 0,
                        feelsLike = 0.0
                    )
                )
            )
        )
    }
}