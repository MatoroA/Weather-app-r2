package dvt.com.weather.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME


@Qualifier
@Retention(RUNTIME)
annotation class DvtDispatcher(val dispatcher: DvtDispatchers)

enum class DvtDispatchers {
    IO,
    Default
}