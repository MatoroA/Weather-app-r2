package dvt.com.weather.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
class NetworkDataModule {

    @Provides
    fun providesJson() = Json {
        ignoreUnknownKeys = true
    }

}