package com.m3.wr15.sample.presentation.screen.search

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.m3.wr15.sample.presentation.component.LoadingDialog

@Composable
fun SearchScreen(innerPadding: PaddingValues, navHostController: NavHostController, vm: SearchViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    val scanOk = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
    val connOk = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED

    LaunchedEffect(scanOk, connOk) {
        if (scanOk && connOk) {
            vm.startDiscoveryScan()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            vm.handleIntent(SearchIntent.StopDiscoveryScan)
        }
    }

    LaunchedEffect(Unit) {
        vm.event.collect { event ->
            when (event) {
                is SearchEvents.NavigateToNext -> {
                    navHostController.navigate(event.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }

        }
    }

    // LoadingDialog
    if (uiState.isLoading) LoadingDialog()

    SearchView(innerPadding, uiState, vm::handleIntent)
}