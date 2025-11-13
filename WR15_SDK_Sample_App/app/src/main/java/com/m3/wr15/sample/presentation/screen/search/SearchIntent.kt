package com.m3.wr15.sample.presentation.screen.search

import android.bluetooth.BluetoothDevice

sealed class SearchIntent {
    data class ConnectToDevice(val btDevice: BluetoothDevice) : SearchIntent()
    data object StopDiscoveryScan : SearchIntent()
}