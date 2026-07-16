package com.m3.ring.scanemul.sample.presentation.screen.s2p

sealed class S2PEvents {
    data class NavigateTo(val route: String) : S2PEvents()
}