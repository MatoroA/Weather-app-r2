package dvt.com.weather.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType
}

enum class DvtFlavor(val dimension: FlavorDimension, val applicationSuffixId: String? = null) {
    DEBUG(dimension = FlavorDimension.contentType, applicationSuffixId = ".demo"),
    RELEASE(dimension = FlavorDimension.contentType)
}

fun configureFlavor(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            DvtFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationSuffixId != null) {
                            applicationIdSuffix = it.applicationSuffixId
                        }
                    }
                }
            }


        }
    }

}