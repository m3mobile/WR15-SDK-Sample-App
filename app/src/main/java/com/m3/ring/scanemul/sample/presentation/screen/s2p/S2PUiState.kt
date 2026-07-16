package com.m3.ring.scanemul.sample.presentation.screen.s2p

import android.graphics.Bitmap

data class S2PUiState(
    val isLoading: Boolean = false,
    val s2pBarcode: Bitmap? = null
)
