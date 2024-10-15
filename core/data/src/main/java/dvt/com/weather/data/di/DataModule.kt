package dvt.com.weather.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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

    @Binds
    fun bindsWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Singleton
    @Binds
    fun bindsUserLocation(impl: LiveLocationManagerImpl): LiveLocationManager

    @Singleton
    @Binds
    fun bindsLiveWeather(impl: LiveWeatherManagerImpl): LiveWeatherManager
}