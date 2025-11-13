package com.m3.wr15.sample.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val event = MutableSharedFlow<MainEvents>()

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.NavigateTo -> {
                navigateToNext(intent.route)
            }
        }
    }

    private fun navigateToNext(route: String) {
        viewModelScope.launch {
            event.emit(MainEvents.NavigateToNextScreen(route))
        }
    }
}