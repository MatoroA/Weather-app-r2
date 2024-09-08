package dvt.com.weather.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


fun Project.configureComposeAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1"
        }
    }

    dependencies {
        add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
        add("implementation", libs.findLibrary("androidx.compose.runtime").get())
        add("implementation", libs.findLibrary("androidx.compose.ui").get())
        add("implementation", libs.findLibrary("androidx.ui.tooling").get())
        add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())

    }


}