package com.m3.ring.scanemul.sample.presentation.screen.search

import android.bluetooth.BluetoothDevice

sealed class SearchIntent {
    data class ConnectToDevice(val btDevice: BluetoothDevice) : SearchIntent()
    data object StopDiscoveryScan : SearchIntent()
}