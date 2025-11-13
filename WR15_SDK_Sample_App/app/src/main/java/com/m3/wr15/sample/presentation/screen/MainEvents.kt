package com.m3.wr15.sample.presentation.screen

sealed class MainEvents {
    data class NavigateToNextScreen(val route: String) : MainEvents()
}