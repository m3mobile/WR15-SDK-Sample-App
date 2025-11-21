package com.m3.wr15.sample.domain

import com.m3.wr15.sdk.api.M3Utils
import com.m3.wr15.sdk.api.SettingCommand
import com.m3.wr15.sdk.api.TransportSession
import com.m3.wr15.sdk.api.listener.DecodingDataListener
import com.m3.wr15.sdk.api.listener.DeviceStateListener
import com.m3.wr15.sdk.api.listener.DisconnectListener
import com.m3.wr15.sdk.api.model.DeviceState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Manages Bluetooth device connection and state.
 */
object BtDeviceManager {
    private var transportSession: TransportSession? = null
    private var isInitialized = false

    // StateFlows to expose connection status
    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    // StateFlow to expose device state
    private val _deviceState = MutableStateFlow(DeviceState())
    val deviceState = _deviceState.asStateFlow()

    // SharedFlow to emit decoding data
    private val _decodingData = MutableSharedFlow<String>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val decodingData = _decodingData.asSharedFlow()

    private val disconnectListener = object : DisconnectListener {
        override fun onDisconnect() {
            dispose()
        }
    }

    private val deviceStateListener = object : DeviceStateListener {
        override fun onDeviceStateChanged(state: DeviceState) {
            _deviceState.value = state
        }
    }

    private val decodingDataListener = object : DecodingDataListener {
        override fun onReceive(decodingData: ByteArray) {
            val dataString = M3Utils.toUtf8String(decodingData)
            _decodingData.tryEmit(dataString)
        }
    }

    fun initialize(transportSession: TransportSession) {
        if (isInitialized) return

        isInitialized = true
        this.transportSession = transportSession
        _isConnected.value = true
        startObservingDecodingData()
        startObservingDeviceState()
        startObservingDisconnect()
    }

    fun disconnect() {
        transportSession?.disconnect(
            onSuccess = { dispose() },
            onFailure = { /* disconnect fail */ }
        )
    }

    fun changeSetting(settingCommand: SettingCommand) {
        transportSession?.setSetting(
            settingCommand,
            onSuccess = {
                // handle success
                // if you are observing device state, you will get updated state via listener
            },
            onFailure = { _, _ ->
                // handle failure
            }
        )
    }

    private fun startObservingDisconnect() {
        transportSession?.addDisconnectListener(disconnectListener)
    }

    private fun startObservingDeviceState() {
        transportSession?.addDeviceStateListener(deviceStateListener)
    }

    private fun startObservingDecodingData() {
        transportSession?.addDecodingDataListener(decodingDataListener)
    }

    private fun dispose() {
        transportSession?.removeDisconnectListener(disconnectListener)
        transportSession?.removeDeviceStateListener(deviceStateListener)
        transportSession?.removeDecodingDataListener(decodingDataListener)

        transportSession = null
        isInitialized = false
        _isConnected.value = false
        _deviceState.value = DeviceState()
    }
}


