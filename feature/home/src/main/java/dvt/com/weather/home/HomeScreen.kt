package dvt.com.weather.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dvt.com.weather.designsystem.R
import dvt.com.weather.designsystem.theme.LocalBackgroundTheme
import dvt.com.weather.designsystem.theme.WeatherTheme
import dvt.com.weather.model.WeatherType
import dvt.com.weather.model.weather.Main
import dvt.com.weather.model.weather.CurrentWeather
import dvt.com.weather.model.weather.Forecast


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

    val horizontalScreenPadding = 8.dp

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = horizontalScreenPadding
                )
        ) {
            Box(
                modifier = Modifier
                    .fillScreenWidth(
                        horizontalPadding = horizontalScreenPadding
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = current.image),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "sunny"
                )

                CurrentDayWeather(
                    title = current.weatherType.name,
                    temperature = state.current.main.temperature
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CurrentDayWeather(title = "Min", temperature = state.current.main.temperatureMin)
                CurrentDayWeather(title = "Current", temperature = state.current.main.temperature)
                CurrentDayWeather(title = "Max", temperature = state.current.main.temperatureMax)
            }

            Box(
                modifier = Modifier
                    .fillScreenWidth(
                        horizontalPadding = horizontalScreenPadding
                    ),
            ) {
                HorizontalDivider()
            }

            Spacer(modifier = Modifier.height(8.dp))

            state.forecasts.forEach { forecast ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = getDayOfWeek(forecast.dt),
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    if (forecast.weather.isNotEmpty())
                        Image(
                            modifier = Modifier.size(
                                20.dp
                            ),
                            painter = painterResource(
                                id = getWeatherIcon(
                                    forecast.weather.first().id
                                )
                            ),
                            contentDescription = ""
                        )

                    Text(
                        text = "${forecast.main.temperature}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

fun getWeatherIcon(weatherId: Int): Int =
    when (weatherId) {
        in 200..799 -> {
            R.mipmap.rain
        }

        800 -> {
            R.mipmap.clear
        }

        else -> {
            R.mipmap.partlysunny
        }
    }

@Composable
fun CurrentDayWeather(modifier: Modifier = Modifier, title: String, temperature: Double) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = temperature.toString())
        Text(text = title)
    }

}

@Composable
private fun Modifier.fillScreenWidth(horizontalPadding: Dp): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints.copy(
                maxWidth = constraints.maxWidth + (horizontalPadding * 2).roundToPx()
            )
        )

        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }


@Preview(showBackground = true)
@Composable
private fun CurrentDayWeatherPrev() {
    WeatherTheme {
        CurrentDayWeather(title = "Min", temperature = 20.5)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeWeatherPreview() {
    WeatherTheme {
        HomeWeather(
            state = HomeUiState.Success(
                current = CurrentWeather(
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
                ),
                forecasts = listOf(
                    Forecast(
                        dt = 1725310800L,
                        main = Main(
                            temperature = 22.0,
                            temperatureMax = 56.0,
                            temperatureMin = 23.4,
                            pressure = 20,
                            humidity = 9,
                            seaLevel = 100,
                            groundLevel = 0,
                            feelsLike = 20.0,
                        ),
                        weather = emptyList()

                    )
                )

            )
        )
    }
}