package com.m3.wr15.sample.presentation.screen.home.configure

import com.m3.wr15.sample.presentation.model.SettingRadioValue
import com.m3.wr15.sdk.api.model.TypeValue

data class RadioDialogUiState(
    var isVisible: Boolean = false,
    var title: String = "",
    var options: List<SettingRadioValue> = emptyList(),
    var selectedOption: SettingRadioValue? = null,
    var onOptionSelected: (TypeValue) -> Unit = {},
    var onDismiss: () -> Unit = {}
)
