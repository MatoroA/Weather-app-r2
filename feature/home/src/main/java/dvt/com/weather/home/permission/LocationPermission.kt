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
import dvt.com.weather.home.permission.LocationPermStatus.Granted
import dvt.com.weather.home.permission.LocationPermStatus.NotGranted

@Composable
fun LocationPermission(
    viewModel: LocationPermissionViewModel = hiltViewModel(),
    granted: @Composable () -> Unit,
) {
    val context = LocalContext.current


    var locationPermsGranted by remember {
        mutableStateOf(
            if (hasPermission(
                    permissions = locationPermissions(),
                    context = context
                )
            ) Granted else NotGranted
        )
    }

    // when permission is not granted, launch
    if (locationPermsGranted == NotGranted) {
        val launcher =
            rememberLauncherForActivityResult(contract = RequestMultiplePermissions()) { map ->
                val granted = map.values.all { it }
                if (granted) {
                    locationPermsGranted = Granted
                } else {
                    viewModel.onUpdateStatus(LocationPermStatus.Denied)
                }
            }

        LaunchedEffect(Unit) {
            launcher.launch(arrayOf(*locationPermissions()))
        }
    } else {
        // update view model and get current location
        viewModel.getLiveLocation()
        granted()
    }
}


fun hasPermission(vararg permissions: String, context: Context): Boolean = permissions.all {
    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}

private fun locationPermissions(): Array<String> = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

