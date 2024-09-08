plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.dvt.weather.hilt)
}

android {
    namespace = "dvt.com.weather.domain"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)
}