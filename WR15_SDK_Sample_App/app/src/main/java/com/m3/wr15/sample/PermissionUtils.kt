package com.m3.wr15.sample

import android.Manifest
import android.os.Build

object PermissionUtils {
    private val isAndroid12Above = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val permissions = if (isAndroid12Above) {
        listOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
        )
    } else {
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
}