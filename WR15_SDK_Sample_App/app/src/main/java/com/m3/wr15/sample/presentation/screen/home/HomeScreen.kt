package com.m3.wr15.sample.presentation.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.m3.wr15.sample.presentation.navigation.Routes

@Composable
fun HomeScreen(innerPadding: PaddingValues, navHostController: NavHostController, vm: HomeViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.event.collect { event ->
            when (event) {
                is HomeEvent.NavigateToNext -> {
                    navHostController.navigate(event.route)
                }

                is HomeEvent.NavigateToMain -> {
                    navHostController.navigate(Routes.MAIN_SCREEN) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }

    HomeView(innerPadding, uiState, vm::handleIntent)
}