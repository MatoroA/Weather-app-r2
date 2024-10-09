plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dvt.weather.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "dvt.com.weather.network"

    defaultConfig {
        buildConfigField(
            "String",
            "WEATHER_API_KEY",
            "\"${project.findProperty("WEATHER_API_KEY")}\""
        )

        buildConfigField(
            "String",
            "BASE_URL",
            "\"${project.findProperty("BASE_URL")}\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.serialisation.converter)
    implementation(libs.retrofit)
    implementation(libs.google.gson)
    implementation(libs.okhttp.logging.intercerptor)
}