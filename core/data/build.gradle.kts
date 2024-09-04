plugins {
    alias(libs.plugins.dvt.weather.android.library)
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

    implementation(libs.dagger.hilt.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}