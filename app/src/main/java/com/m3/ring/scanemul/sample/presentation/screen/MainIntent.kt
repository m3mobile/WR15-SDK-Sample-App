package com.m3.ring.scanemul.sample.presentation.screen

sealed class MainIntent {
    data class NavigateTo(val route: String): MainIntent()
}