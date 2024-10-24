plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dvt.weather.hilt)
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "dvt.com.weather.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }
}

secrets {
    defaultPropertiesFileName = "secrets.default.properties"
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.serialisation.converter)
    implementation(libs.retrofit)
    implementation(libs.google.gson)
    implementation(libs.okhttp.logging.intercerptor)
}