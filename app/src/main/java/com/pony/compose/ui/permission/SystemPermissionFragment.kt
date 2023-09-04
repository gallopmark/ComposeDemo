package com.pony.compose.ui.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.pony.compose.base.BaseFragment
import com.pony.compose.common.CommonHorizontalSpacer
import com.pony.compose.common.CommonTextButton

/**
 *Created by pony on 2022/8/1
 *Description->
 */
class SystemPermissionFragment : BaseFragment() {

    private lateinit var mActivityResultLauncher: ActivityResultLauncher<String>
    private val cameraPermission = android.Manifest.permission.CAMERA
    private val cameraState = mutableStateOf("")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            cameraState.value = if (isGranted) {
                "Camera permission Granted"
            } else {
                if (!shouldShowRequestPermissionRationale(cameraPermission)) {
                    "The camera is important for this app. Please grant the permission."
                } else {
                    "Camera permission required for this feature to be available. Please grant the permission."
                }
            }
        }
    }


    @Composable
    override fun CreateContentView() {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CameraPermissionComponent()
        }
    }

    @Composable
    private fun CameraPermissionComponent() {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = cameraState.value)
            CommonHorizontalSpacer()
            val context = LocalContext.current
            CommonTextButton(onClick = {
                if (!shouldShowRequestPermissionRationale(cameraPermission)) {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.parse("package:${context.packageName}")
                    context.startActivity(intent)
                } else {
                    mActivityResultLauncher.launch(cameraPermission)
                }
            }, text = "拍照权限")
        }
    }

    override fun onDestroyView() {
        mActivityResultLauncher.unregister()
        super.onDestroyView()
    }
}