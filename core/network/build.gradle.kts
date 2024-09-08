plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dvt.weather.hilt)
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
}