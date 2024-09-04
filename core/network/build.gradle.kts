plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dvt.com.weather.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.dagger.hilt.android)
}