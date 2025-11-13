package com.m3.wr15.sample

import android.app.Application
import com.m3.wr15.sdk.api.ConnectionType
import com.m3.wr15.sdk.api.M3Utils
import com.m3.wr15.sdk.api.M3Wr15Sdk
import com.m3.wr15.sdk.api.M3Wr15SdkConfig

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeSdk()
    }

    private fun initializeSdk() {
        val config = M3Wr15SdkConfig.builder()
            .connectionType(ConnectionType.SPP)
            .enableLog(true)
            .build()

        M3Wr15Sdk.initialize(this, config)
        M3Utils
    }
}