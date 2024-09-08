import com.android.build.gradle.LibraryExtension
import dvt.com.weather.convention.configureKotlinAndroid
import dvt.com.weather.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


class AndroidFeaturePluginConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dvt.weather.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("dvt.weather.android.compose.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                buildFeatures  {
                    compose =  true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.1"
                }
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
            }
        }
    }

}