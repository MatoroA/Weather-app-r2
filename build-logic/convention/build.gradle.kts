import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
//    compileOnly(libs.android.tools.common)
    compileOnly(libs.android.tools.build.gradle)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "dvt.weather.android.application"
            implementationClass = "AndroidApplicationPluginConvention"
        }
        register("androidLibrary") {
            id = "dvt.weather.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinJvm") {
            id = "dvt.weather.kotlin.jvm"
            implementationClass = "KotlinJvmConventionPlugin"
        }
        register("androidFeature") {
            id = "dvt.weather.android.feature"
            implementationClass = "AndroidFeaturePluginConvention"
        }
    }
}