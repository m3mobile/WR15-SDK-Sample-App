package com.m3.ring.scanemul.sample.presentation.screen.home.configure

data class StrTextFieldDialogUiState(
    var isVisible: Boolean = false,
    var title: String = "",
    var subTitle: String? = null,
    var currentValue: String = "",
    var onDismiss: () -> Unit = {},
    var onConfirm: (String) -> Unit = {}
)