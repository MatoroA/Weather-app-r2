package dvt.com.weather.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dvt.com.weather.data.util.SyncWeatherManager
import dvt.com.weather.weather.status.SyncWeatherManagerImpl


@Module
@InstallIn(SingletonComponent::class)
interface SyncWorkerModule {

    @Binds
    fun bindsSyncWeatherManager(impl: SyncWeatherManagerImpl): SyncWeatherManager
}