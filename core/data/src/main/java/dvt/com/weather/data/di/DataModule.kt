package dvt.com.weather.data.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.data.repository.WeatherRepositoryImpl
import dvt.com.weather.data.util.LiveLocationManagerImpl
import dvt.com.weather.data.util.LiveLocationManager
import dvt.com.weather.data.util.LiveWeatherManager
import dvt.com.weather.data.util.LiveWeatherManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object {
        @Provides
        fun providesFusedLocation(@ApplicationContext context: Context): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        @Provides
        fun providesGeoCoder(@ApplicationContext context: Context): Geocoder = Geocoder(context)
    }

    @Binds
    fun bindsWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Singleton
    @Binds
    fun bindsUserLocation(impl: LiveLocationManagerImpl): LiveLocationManager

    @Singleton
    @Binds
    fun bindsLiveWeather(impl: LiveWeatherManagerImpl): LiveWeatherManager

}