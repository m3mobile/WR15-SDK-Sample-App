package com.m3.wr15.sample.presentation.screen.home.configure

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.m3.wr15.sample.presentation.navigation.Routes
import com.m3.wr15.sample.presentation.screen.home.HomeEvent
import com.m3.wr15.sample.presentation.screen.home.configure.component.IntTextFieldDialog
import com.m3.wr15.sample.presentation.screen.home.configure.component.RadioDialogView
import com.m3.wr15.sample.presentation.screen.home.configure.component.StrTextFieldDialog

@Composable
fun SettingScreen(innerPadding: PaddingValues, navController: NavHostController, vm: ConfigureViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        vm.event.collect { event ->
            when (event) {
                is ConfigureEvent.NavigateToHome -> {
                    navController.navigate(Routes.MAIN_SCREEN) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = false }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            }
        }
    }

    // Int용 TextFieldDialog
    if (uiState.intTextFiledDialogState.isVisible) {
        IntTextFieldDialog(
            title = uiState.intTextFiledDialogState.title,
            currentValue = uiState.intTextFiledDialogState.currentValue,
            onConfirm = uiState.intTextFiledDialogState.onConfirm,
            onDismiss = uiState.intTextFiledDialogState.onDismiss,
        )
    }

    if (uiState.radioDialogState.isVisible) {
        RadioDialogView(
            title = uiState.radioDialogState.title,
            options = uiState.radioDialogState.options,
            selectedOption = uiState.radioDialogState.selectedOption,
            onOptionSelected = uiState.radioDialogState.onOptionSelected,
            onDismiss = uiState.radioDialogState.onDismiss
        )
    }

    if (uiState.strTextFiledDialogState.isVisible) {
        StrTextFieldDialog(
            title = uiState.strTextFiledDialogState.title,
            subTitle = uiState.strTextFiledDialogState.subTitle,
            currentValue = uiState.strTextFiledDialogState.currentValue,
            onConfirm = uiState.strTextFiledDialogState.onConfirm,
            onDismiss = uiState.strTextFiledDialogState.onDismiss,
        )
    }

    ConfigureView(innerPadding, uiState, vm::handleIntent)
}