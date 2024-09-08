plugins {
    alias(libs.plugins.dvt.weather.android.library)
}

android {
    namespace = "dvt.com.weather.designsystem"
    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui.graphics)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.material3)
}