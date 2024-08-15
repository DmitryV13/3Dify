package com.app.arcore.helpers

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/** Helper to ask camera permission.  */
object CameraPermissionHelper {
    private const val CAMERA_PERMISSION_CODE = 0
    private const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    val CAMERA_PERMISSION_REQUEST_CODE = 0

    /** Check to see we have the necessary permissions for this app.  */
    fun hasCameraPermission(activity: Activity?): Boolean {
        return (ContextCompat.checkSelfPermission(activity!!, CAMERA_PERMISSION)
                == PackageManager.PERMISSION_GRANTED)
    }
    object CameraPermissionHelper {
        const val CAMERA_PERMISSION_REQUEST_CODE = 0

        fun hasCameraPermission(activity: Activity): Boolean {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED
        }

        fun requestCameraPermission(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    /** Check to see we have the necessary permissions for this app, and ask for them if we don't.  */
    fun requestCameraPermission(activity: Activity?) {
        ActivityCompat.requestPermissions(
            activity!!, arrayOf(CAMERA_PERMISSION), CAMERA_PERMISSION_CODE
        )
    }

    /** Check to see if we need to show the rationale for this permission.  */
    fun shouldShowRequestPermissionRationale(activity: Activity?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity!!, CAMERA_PERMISSION)
    }

    /** Launch Application Setting to grant permission.  */
    fun launchPermissionSettings(activity: Activity) {
        val intent = Intent()
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.setData(Uri.fromParts("package", activity.packageName, null))
        activity.startActivity(intent)
    }
}