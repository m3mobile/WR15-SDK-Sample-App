package com.m3.wr15.sample.presentation.screen.s2p

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CircularProgressIndicator
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
fun S2PScreen(innerPadding: PaddingValues, navHostController: NavHostController, vm: S2PViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scanOk = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
    val connOk = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED

    LaunchedEffect(Unit) {
        if (scanOk && connOk) vm.startS2PScan()
    }

    DisposableEffect(Unit) {
        onDispose {
            if (scanOk) vm.stopS2PScan()
        }
    }

    LaunchedEffect(Unit) {
        vm.event.collect { event ->
            when (event) {
                is S2PEvents.NavigateTo -> {
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

    S2PView(innerPadding, uiState)
}