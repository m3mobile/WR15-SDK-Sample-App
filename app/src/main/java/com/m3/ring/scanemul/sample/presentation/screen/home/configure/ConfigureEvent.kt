package com.m3.ring.scanemul.sample.presentation.screen.home.configure

sealed class ConfigureEvent {
    data object NavigateToHome : ConfigureEvent()
}