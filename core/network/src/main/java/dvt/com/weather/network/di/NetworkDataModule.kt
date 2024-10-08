package dvt.com.weather.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dvt.com.weather.network.WeatherDataSource
import dvt.com.weather.network.WeatherDataSourceImpl
import dvt.com.weather.network.util.ServiceInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkDataModule {

    @Provides
    fun providesJson() = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesRetrofit(networkJson: Json): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ServiceInterceptor("505b2db913befd32000cc5d021a28249"))
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    )
                    .build()
            )
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
    }

    @Provides
    fun providesWeatherDataSource(retrofit: Retrofit): WeatherDataSource {
        return WeatherDataSourceImpl(retrofit)
    }

}