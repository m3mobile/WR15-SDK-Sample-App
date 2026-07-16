package com.m3.ring.scanemul.sample.presentation.screen.search

sealed class SearchEvents {
    data class NavigateToNext(val route: String): SearchEvents()
}