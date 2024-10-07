package dvt.com.weather.assessment.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dvt.com.weather.designsystem.theme.LocalBackgroundTheme
import dvt.com.weather.designsystem.theme.WeatherTheme
import dvt.com.weather.home.HomeRoute
import dvt.com.weather.model.CurrentLocation
import dvt.com.weather.model.WeatherType
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : LocationActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme(
                weatherType = WeatherType.SUNNY
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

    override fun onCurrentLocation(location: CurrentLocation?) {
        viewModel.onUpdateLocation(location)
    }
}

