import dvt.com.weather.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "dagger.hilt.android.plugin")
            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                "implementation"(libs.findLibrary("dagger.hilt.android").get())
                "ksp"(libs.findLibrary("dagger.hilt.compiler").get())
            }
        }
    }

}