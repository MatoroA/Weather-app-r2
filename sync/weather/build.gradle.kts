plugins {
    alias(libs.plugins.dvt.weather.android.library)
}

android {
    namespace = "dvt.com.weather.weather"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

}