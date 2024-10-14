//package dvt.com.weather.common
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.content.ContextCompat
//
//@Composable
//fun LocationPermission() {
//    val context = LocalContext.current
//    val locationPermsGranted by remember {
//        mutableStateOf(
//            hasPermission(
//                permissions = locationPermissions(),
//                context = context
//            )
//        )
//    }
//
//    // when permission is not granted, launch
//    if (!locationPermsGranted) {
//        val launcher =
//            rememberLauncherForActivityResult(contract = RequestMultiplePermissions()) { map ->
//                val granted = map.values.all { it }
//            }
//
//        LaunchedEffect(Unit) {
//            launcher.launch(arrayOf(*locationPermissions()))
//        }
//    } else {
//        // update view model and get current location
//    }
//}
//
//fun hasPermission(vararg permissions: String, context: Context): Boolean = permissions.all {
//    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
//}
//
//private fun locationPermissions(): Array<String> = arrayOf(
//    Manifest.permission.ACCESS_FINE_LOCATION,
//    Manifest.permission.ACCESS_COARSE_LOCATION
//)
//
