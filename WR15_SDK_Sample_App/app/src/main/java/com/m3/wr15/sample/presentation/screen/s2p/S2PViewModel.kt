package com.m3.wr15.sample.presentation.screen.s2p

import android.Manifest
import android.bluetooth.BluetoothDevice
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m3.wr15.sample.domain.BtDeviceManager
import com.m3.wr15.sample.presentation.navigation.Routes
import com.m3.wr15.sdk.api.M3Wr15Sdk
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
            val barcode = M3Wr15Sdk.getBarcodeBitmapForS2P(500, 100)
            _uiState.update { it.copy(s2pBarcode = barcode) }
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    fun startS2PScan() {
        viewModelScope.launch {
            M3Wr15Sdk.startS2PScan(
                onDeviceFound = { device ->
                    connectToDevice(device)
                },
                onScanFailed = {}
            )
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN])
    fun stopS2PScan() {
        viewModelScope.launch {
            M3Wr15Sdk.stopS2PScan()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    fun connectToDevice(device: BluetoothDevice) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            M3Wr15Sdk.connectToDevice(
                device,
                onSuccess = { transportSession ->
                    _uiState.update { it.copy(isLoading = false) }
                    BtDeviceManager.initialize(transportSession)
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

    private fun navigateToNextScreen(route: String) {
        viewModelScope.launch {
            event.emit(S2PEvents.NavigateTo(route))
        }
    }
}