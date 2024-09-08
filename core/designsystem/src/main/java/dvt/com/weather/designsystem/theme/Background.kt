package dvt.com.weather.designsystem.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Background(
    val color: Color = Color.Unspecified,
    @DrawableRes val image: Int? = null,
)
