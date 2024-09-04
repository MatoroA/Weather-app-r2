plugins {
    alias(libs.plugins.dvt.weather.android.feature)
}

android {
    namespace = "dvt.com.weather.home"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

}