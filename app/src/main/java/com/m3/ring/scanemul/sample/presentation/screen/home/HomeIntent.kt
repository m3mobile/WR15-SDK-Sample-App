package com.m3.ring.scanemul.sample.presentation.screen.home

sealed class HomeIntent {
    data object Disconnect: HomeIntent()
    data class NavigateToNext(val route: String): HomeIntent()
}