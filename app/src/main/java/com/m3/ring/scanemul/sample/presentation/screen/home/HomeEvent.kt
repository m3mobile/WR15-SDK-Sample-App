package com.m3.ring.scanemul.sample.presentation.screen.home

sealed class HomeEvent {
    data class NavigateToNext(val route: String) : HomeEvent()
    data object NavigateToMain : HomeEvent()
}