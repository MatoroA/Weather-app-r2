package dvt.com.weather.home.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationPermission(
    viewModel: LocationPermissionViewModel = hiltViewModel(),
    granted: @Composable () -> Unit,
) {
    val context = LocalContext.current


    var locationStatus by remember {
        mutableStateOf(
            if (hasPermission(
                    permissions = locationPermissions(),
                    context = context
                )
            ) LocationPermStatus.Granted else LocationPermStatus.NotGranted
        )
    }

    // when permission is not granted, launch

    when (locationStatus) {
        LocationPermStatus.Granted -> {
            viewModel.getLiveLocation()
            granted()
        }

        LocationPermStatus.NotGranted -> {
            val launcher =
                rememberLauncherForActivityResult(contract = RequestMultiplePermissions()) { map ->
                    val granted = map.values.all { it }
                    if (granted) {
                        locationStatus = LocationPermStatus.Granted
                    } else {
                        locationStatus = LocationPermStatus.Denied
                    }
                }

            LaunchedEffect(Unit) {
                launcher.launch(arrayOf(*locationPermissions()))
            }
        }

        LocationPermStatus.Denied -> {
            // app settings pop up
        }
    }
}


fun hasPermission(vararg permissions: String, context: Context): Boolean = permissions.all {
    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}

private fun locationPermissions(): Array<String> = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

