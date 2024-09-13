package dvt.com.weather.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dvt.com.weather.designsystem.theme.LocalBackgroundTheme
import dvt.com.weather.designsystem.theme.WeatherTheme
import dvt.com.weather.home.HomeRoute
import dvt.com.weather.model.WeatherType

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme(
                theme = WeatherType.RAINY
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

