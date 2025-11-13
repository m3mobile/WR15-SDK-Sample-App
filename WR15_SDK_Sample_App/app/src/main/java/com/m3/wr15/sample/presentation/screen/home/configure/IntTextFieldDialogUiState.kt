package com.m3.wr15.sample.presentation.screen.home.configure

data class IntTextFieldDialogUiState(
    val isVisible: Boolean = false,
    val title: String = "",
    val currentValue: Int = 0,
    val onDismiss: () -> Unit = {},
    val onConfirm: (Int) -> Unit = {}
)