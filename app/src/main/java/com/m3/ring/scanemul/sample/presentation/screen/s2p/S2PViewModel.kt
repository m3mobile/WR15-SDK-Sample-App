package com.m3.ring.scanemul.sample.presentation.screen.s2p

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m3.ring.scanemul.sample.domain.BtDeviceManager
import com.m3.ring.scanemul.sample.presentation.navigation.Routes
import com.m3.ring.scanemul.sdk.api.core.RingScanEmul
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class S2PViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(S2PUiState())
    val uiState = _uiState.asStateFlow()

    val event = MutableSharedFlow<S2PEvents>()

    init {
        loadS2PBarcode()
    }

    private fun loadS2PBarcode() {

        viewModelScope.launch {
            val barcode = RingScanEmul.getBarcodeBitmapForS2P(500, 100)
            _uiState.update { it.copy(s2pBarcode = barcode) }
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    fun startS2PScan() {
        viewModelScope.launch {
            RingScanEmul.startS2PScan(
                onDeviceFound = { device ->
                    connectToDevice(device)
                },
                onScanFailed = { code, msg ->
                    Log.e("S2PViewModel", "S2P Scan failed: $code, $msg")
                    // Error handling
                }
            )
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN])
    fun stopS2PScan() {
        viewModelScope.launch {
            RingScanEmul.stopS2PScan()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    fun connectToDevice(device: BluetoothDevice) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            RingScanEmul.connectToDevice(
                device,
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false) }
                    BtDeviceManager.initialize(RingScanEmul.getTransportSession())
                    navigateToNextScreen(Routes.HOME_SCREEN)
                },
                onFailure = { code, msg ->
                    _uiState.update { it.copy(isLoading = false) }
                    // Error handling
                    startS2PScan()
                }
            )
        }
    }


    /**
     * Using coroutine to scan and connect sequentially
     */
    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    fun startS2PScanAndConnect() {
        viewModelScope.launch {
            try {
                val device = RingScanEmul.startS2PScan()
                _uiState.update { it.copy(isLoading = true) }
                RingScanEmul.connectToDevice(device)
                BtDeviceManager.initialize(RingScanEmul.getTransportSession())
                navigateToNextScreen(Routes.HOME_SCREEN)
            } catch (e: Exception) {
                // Error handling
                startS2PScanAndConnect()
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun navigateToNextScreen(route: String) {
        viewModelScope.launch {
            event.emit(S2PEvents.NavigateTo(route))
        }
    }
}
