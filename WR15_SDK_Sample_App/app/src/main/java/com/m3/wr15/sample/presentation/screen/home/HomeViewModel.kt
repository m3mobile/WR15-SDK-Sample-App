package com.m3.wr15.sample.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m3.wr15.sample.domain.BtDeviceManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        observeBtDevice()
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Disconnect -> {
                disconnect()
            }

            is HomeIntent.NavigateToNext -> {
                navigateToNext(intent.route)
            }
        }
    }

    private fun observeBtDevice() {
        BtDeviceManager.deviceState
            .onEach { deviceState ->
                _uiState.update {
                    it.copy(
                        deviceName = deviceState.deviceInfo.name.orEmpty(),
                        deviceMac = deviceState.deviceInfo.macAddress.orEmpty(),
                        deviceSerialNum = deviceState.deviceInfo.serialNumber.orEmpty(),
                        battery = deviceState.deviceInfo.batteryPercent
                    )
                }
            }
            .launchIn(viewModelScope)

        BtDeviceManager.decodingData
            .onEach { data ->
                _uiState.update {
                    it.copy(
                        decodingDataList = it.decodingDataList + data
                    )
                }
            }
            .launchIn(viewModelScope)

        BtDeviceManager.isConnected
            .onEach { isConnected ->
                if (!isConnected) {
                    _event.emit(HomeEvent.NavigateToMain)
                    resetState()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun navigateToNext(nextRoute: String) {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToNext(nextRoute))
        }
    }

    private fun disconnect() {
        BtDeviceManager.disconnect()
    }

    private fun resetState() {
        _uiState.value = HomeUiState()
    }
}