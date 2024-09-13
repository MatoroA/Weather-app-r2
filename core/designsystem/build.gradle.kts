plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.dvt.weather.compose.library)
}

android {
    namespace = "dvt.com.weather.designsystem"
    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.ui.graphics)
    api(libs.androidx.material3)
}