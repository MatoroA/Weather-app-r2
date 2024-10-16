package dvt.com.weather.assessment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dvt.com.weather.data.util.LiveWeatherManager
import dvt.com.weather.designsystem.theme.LocalBackgroundTheme
import dvt.com.weather.designsystem.theme.WeatherTheme
import dvt.com.weather.home.HomeRoute
import dvt.com.weather.model.WeatherType
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var liveWeatherManager: LiveWeatherManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val appState = rememberWeatherAppState(liveWeatherManager = liveWeatherManager)

            val weather by appState.currentTemperature.collectAsStateWithLifecycle()

            WeatherTheme(
                weatherType = WeatherType.CLOUDY
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = LocalBackgroundTheme.current.color
                ) { innerPadding ->
                    HomeRoute(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

}

