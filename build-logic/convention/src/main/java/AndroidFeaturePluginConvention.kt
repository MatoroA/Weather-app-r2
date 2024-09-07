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
                val bom = libs.findLibrary("androidx.compose.bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findLibrary("androidx.compose.runtime").get())
                add("implementation", libs.findLibrary("androidx.compose.ui").get())
            }
        }
    }

}