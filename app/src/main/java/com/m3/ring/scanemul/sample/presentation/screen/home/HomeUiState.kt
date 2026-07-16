package com.m3.ring.scanemul.sample.presentation.screen.home

data class HomeUiState(
    val deviceName: String = "",
    val deviceMac: String = "",
    val deviceSerialNum: String = "",
    val battery: Int? = 0,
    val decodingDataList: List<String> = emptyList(),
)
