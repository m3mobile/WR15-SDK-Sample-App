package com.m3.wr15.sample.presentation.screen.search

sealed class SearchEvents {
    data class NavigateToNext(val route: String): SearchEvents()
}