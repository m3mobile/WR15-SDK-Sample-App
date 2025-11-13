package com.m3.wr15.sample.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.m3.wr15.sample.PermissionUtils

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(padding: PaddingValues, navController: NavHostController, vm: MainViewModel= viewModel()) {
    val permissionsState = rememberMultiplePermissionsState(permissions = PermissionUtils.permissions)

    LaunchedEffect(Unit) {
        if (!permissionsState.allPermissionsGranted) permissionsState.launchMultiplePermissionRequest()
    }

    LaunchedEffect(Unit) {
        vm.event.collect { event ->
            when (event) {
                is MainEvents.NavigateToNextScreen -> {
                    navController.navigate(event.route)
                }
            }
        }
    }

    MainView(padding, vm::handleIntent)
}