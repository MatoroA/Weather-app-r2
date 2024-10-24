plugins {
    alias(libs.plugins.dvt.weather.android.library)
    alias(libs.plugins.dvt.weather.hilt)
}

android {
    namespace = "dvt.com.weather.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

}

dependencies {
    api(projects.core.model)
    api(projects.core.network)
    api(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.google.play.location)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}