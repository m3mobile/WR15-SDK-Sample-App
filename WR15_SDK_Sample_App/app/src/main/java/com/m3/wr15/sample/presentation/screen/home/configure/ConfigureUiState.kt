package com.m3.wr15.sample.presentation.screen.home.configure

import com.m3.wr15.sdk.api.model.DeviceState

data class ConfigureUiState(
    val deviceState: DeviceState = DeviceState(),
    val radioDialogState: RadioDialogUiState = RadioDialogUiState(),
    val strTextFiledDialogState: StrTextFieldDialogUiState = StrTextFieldDialogUiState(),
    val intTextFiledDialogState: IntTextFieldDialogUiState = IntTextFieldDialogUiState()
)
