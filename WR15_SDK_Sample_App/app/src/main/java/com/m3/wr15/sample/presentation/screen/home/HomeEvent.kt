package com.m3.wr15.sample.presentation.screen.home

sealed class HomeEvent {
    data class NavigateToNext(val route: String) : HomeEvent()
    data object NavigateToMain : HomeEvent()
}