package com.m3.ring.scanemul.sample

import android.app.Application
import com.m3.ring.scanemul.sdk.api.core.ConnectionType
import com.m3.ring.scanemul.sdk.api.core.RingScanEmul
import com.m3.ring.scanemul.sdk.api.core.RingScanEmulSdkConfig

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeSdk()
    }

    private fun initializeSdk() {
        val config = RingScanEmulSdkConfig.builder()
            .connectionType(ConnectionType.SPP)
            .enableLog(true)
            .build()

        RingScanEmul.initialize(this, config)
    }
}