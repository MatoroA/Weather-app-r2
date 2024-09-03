package dvt.com.weather.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.demo.DemoOpenWeatherDatasource

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun bindsOpenWeather(impl: DemoOpenWeatherDatasource): WeatherDataSource
}