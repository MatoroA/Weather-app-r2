package dvt.com.weather.assessment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dvt.com.weather.data.util.CompositeLocationWeatherWorker
import javax.inject.Inject


@HiltAndroidApp
class DvtWeatherApplication : Application() {

    @Inject
    lateinit var compositeLocationWeatherWorker: CompositeLocationWeatherWorker

    override fun onCreate() {
        super.onCreate()

        compositeLocationWeatherWorker()
    }
}