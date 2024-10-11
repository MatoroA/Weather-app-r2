package dvt.com.weather.assessment.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dvt.com.weather.designsystem.theme.LocalBackgroundTheme
import dvt.com.weather.designsystem.theme.WeatherTheme
import dvt.com.weather.home.HomeRoute
import dvt.com.weather.model.WeatherType
import dvt.com.weather.model.weather.CurrentWeather

@AndroidEntryPoint
class MainActivity : LocationActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val appState = rememberWeatherAppState(dvtLocationManager = dvtLocationManager)

            val weather by appState.currentTemperature.collectAsStateWithLifecycle()

            WeatherTheme(
                weatherType = weather.getWeatherType()
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = LocalBackgroundTheme.current.color
                ) { innerPadding ->
                    HomeRoute(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun CurrentWeather?.getWeatherType(): WeatherType {
        if (this == null) {
            return WeatherType.UNSPECIFIED
        }

        return weather.firstOrNull()?.let {
            when (it.id) {
                in 200..799 -> WeatherType.RAINY
                800 -> WeatherType.SUNNY
                else -> WeatherType.CLOUDY
            }
        } ?: WeatherType.UNSPECIFIED
    }

}

