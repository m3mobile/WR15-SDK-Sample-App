package com.m3.wr15.sample.presentation.screen.s2p

sealed class S2PEvents {
    data class NavigateTo(val route: String) : S2PEvents()
}