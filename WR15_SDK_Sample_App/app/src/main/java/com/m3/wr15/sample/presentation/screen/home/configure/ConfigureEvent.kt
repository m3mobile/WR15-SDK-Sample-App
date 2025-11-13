package com.m3.wr15.sample.presentation.screen.home.configure

sealed class ConfigureEvent {
    data object NavigateToHome : ConfigureEvent()
}