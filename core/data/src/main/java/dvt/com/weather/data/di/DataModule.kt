package dvt.com.weather.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dvt.com.weather.data.repository.WeatherRepository
import dvt.com.weather.data.repository.WeatherRepositoryImpl
import dvt.com.weather.data.util.CurrentLocationWeatherImpl
import dvt.com.weather.data.util.CurrentLocationWeather
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Singleton
    @Binds
    fun bindsUserLocation(impl: CurrentLocationWeatherImpl): CurrentLocationWeather

}