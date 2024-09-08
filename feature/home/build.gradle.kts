plugins {
    alias(libs.plugins.dvt.weather.android.feature)
    alias(libs.plugins.dvt.weather.hilt)
}

android {
    namespace = "dvt.com.weather.home"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.data)

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.compose.foundation:foundation:1.7.0")
    implementation(libs.androidx.lifecycle.runtime.compose)
}