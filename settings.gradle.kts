pluginManagement {
    // this included build is added on the plugin management bloc, because it defines plugins:
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherApp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":core:network")
include(":core:designsystem")
include(":core:model")
include(":core:data")
include(":feature")
include(":feature:home")
include(":core:domain")
include(":core:common")
include(":core:database")
include(":sync")
include(":sync:weather")
