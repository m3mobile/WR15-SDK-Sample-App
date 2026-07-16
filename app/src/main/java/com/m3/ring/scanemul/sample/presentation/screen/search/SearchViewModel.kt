package com.m3.ring.scanemul.sample.presentation.screen.search

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m3.ring.scanemul.sample.domain.BtDeviceManager
import com.m3.ring.scanemul.sample.presentation.navigation.Routes
import com.m3.ring.scanemul.sdk.api.core.RingScanEmul
import com.m3.ring.scanemul.sdk.api.listener.DiscoveryScanResultListener
import com.m3.ring.scanemul.sdk.api.model.scan.DiscoveryDeviceModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.util.Locale

class SearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchEvents>()
    val event = _event.asSharedFlow()

    private var autoRestart = true
    private var scanJob: Job? = null

    fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.ConnectToDevice -> {
                connectToDevice(intent.btDevice)
            }

            is SearchIntent.StopDiscoveryScan -> {
                stopDiscoveryScan()
            }
        }
    }

    @Suppress("MissingPermission")
    private fun connectToDevice(btDevice: BluetoothDevice) {
        _uiState.update { it.copy(isLoading = true) }
        RingScanEmul.connectToDevice(
            device = btDevice,
            onSuccess = {
                _uiState.update { it.copy(isLoading = false) }
                BtDeviceManager.initialize(RingScanEmul.getTransportSession())
                navigateToNextScreen(Routes.HOME_SCREEN)
            },
            onFailure = { code, msg ->
                Log.i("SearchViewModel", "Failed to connect to device: ${btDevice.address} / $code : $msg")
                _uiState.update { it.copy(isLoading = false) }
                // handle failure
            }
        )
    }


    /**
     * Start discovery Scan for RingScanEmul devices.
     *
     * It will keep restarting the scan automatically until `autoRestart` is set to false.
     */
    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    fun startDiscoveryScan() {
        if (scanJob?.isActive == true) return

        autoRestart = true
        scanJob = viewModelScope.launch {
            while (isActive && autoRestart) {
                RingScanEmul.startDiscoveryScan(object : DiscoveryScanResultListener {
                    override fun onDeviceFound(device: DiscoveryDeviceModel) {
                        _uiState.update { state ->
                            val new = device.btDevice
                            val mac = new.address?.normalizeMac() ?: return@update state

                            val exists = state.foundBtDevices.any { it.address?.normalizeMac() == mac }
                            if (exists) state
                            else state.copy(foundBtDevices = state.foundBtDevices + new)
                        }
                    }

                    override fun onError(errorCode: Int, errorMsg: String) {
                        Log.e("SearchViewModel", "Discovery scan error: $errorMsg / $errorCode")
                        autoRestart = false
                    }

                    override fun onFinished() {
                        Log.i("SearchViewModel", "Discovery scan finished")
                        // Handle scan finished
                    }

                    override fun onStarted() {
                        Log.i("SearchViewModel", "Discovery scan started")
                        // Handle scan started
                    }
                })

                if (!autoRestart) break
                yield()
            }
        }
    }

    private fun stopDiscoveryScan() {
        autoRestart = false
        scanJob?.cancel()
        scanJob = null

        viewModelScope.launch {
            RingScanEmul.stopDiscoveryScan()
        }
    }

    private fun navigateToNextScreen(route: String) {
        viewModelScope.launch {
            _event.emit(SearchEvents.NavigateToNext(route))
        }
    }

    private fun String.normalizeMac(): String =
        replace(":", "").replace("-", "").lowercase(Locale.US)
}