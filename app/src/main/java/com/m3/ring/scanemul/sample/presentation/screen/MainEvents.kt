package com.m3.ring.scanemul.sample.presentation.screen

sealed class MainEvents {
    data class NavigateToNextScreen(val route: String) : MainEvents()
}