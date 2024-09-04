import com.android.build.gradle.LibraryExtension
import dvt.com.weather.convention.configureKotlinAndroid
import dvt.com.weather.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


class AndroidFeaturePluginConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dvt.weather.android.library")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureKotlinAndroid(extension)
        }
    }

}