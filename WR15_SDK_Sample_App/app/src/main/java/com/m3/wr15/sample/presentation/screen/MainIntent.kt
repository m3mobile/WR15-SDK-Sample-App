package com.m3.wr15.sample.presentation.screen

sealed class MainIntent {
    data class NavigateTo(val route: String): MainIntent()
}