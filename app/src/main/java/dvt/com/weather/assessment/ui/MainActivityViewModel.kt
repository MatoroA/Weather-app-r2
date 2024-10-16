package dvt.com.weather.assessment.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dvt.com.weather.data.util.LiveLocationManager
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val liveLocationManager: LiveLocationManager,
) : ViewModel() {
}