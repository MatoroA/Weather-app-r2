package dvt.com.weather.assessment

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat


fun ComponentActivity.hasPermissions(vararg permissions: String): Boolean = permissions.all {
    ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

fun ComponentActivity.requestPermissions(requestCode: Int, vararg permissions: String) {
    ActivityCompat.requestPermissions(
        this,
        permissions,
        requestCode
    )
}