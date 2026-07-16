package com.m3.ring.scanemul.sample.presentation.screen.search

import android.bluetooth.BluetoothDevice

data class SearchUiState(
    val isLoading: Boolean = false,
    val foundBtDevices: List<BluetoothDevice> = emptyList()
)
