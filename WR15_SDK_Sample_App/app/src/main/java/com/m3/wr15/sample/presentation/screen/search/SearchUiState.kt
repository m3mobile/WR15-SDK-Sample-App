package com.m3.wr15.sample.presentation.screen.search

import android.bluetooth.BluetoothDevice

data class SearchUiState(
    val isLoading: Boolean = false,
    val foundBtDevices: List<BluetoothDevice> = emptyList()
)
