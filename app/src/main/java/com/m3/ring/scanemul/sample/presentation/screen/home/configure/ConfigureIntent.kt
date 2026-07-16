package com.m3.ring.scanemul.sample.presentation.screen.home.configure

import com.m3.ring.scanemul.sample.presentation.model.SettingRadioValue
import com.m3.ring.scanemul.sdk.api.model.TypeValue
import com.m3.ring.scanemul.sdk.api.settings.SettingCommand

sealed class ConfigureIntent {
    data class ShowIntTextFieldDialog(
        val title: String,
        val currentValue: Int,
        val onConfirm: (Int) -> Unit,
        val onDismiss: () -> Unit = {}
    ) : ConfigureIntent()

    data class ChangeSetting(
        val settingCommand: SettingCommand
    ) : ConfigureIntent()

    data class ShowRadioDialog(
        val title: String,
        val options: List<SettingRadioValue>,
        val selectedOption: SettingRadioValue?,
        val onOptionSelected: (TypeValue) -> Unit,
        val onDismiss: () -> Unit = {}
    ) : ConfigureIntent()

    data class ShowStrTextFieldDialog(
        val title: String,
        val subtitle: String? = null,
        val currentValue: String,
        val onConfirm: (String) -> Unit,
        val onDismiss: () -> Unit = {}
    ) : ConfigureIntent()

    data object HideRadioDialog : ConfigureIntent()
    data object HideStrTextFieldDialog : ConfigureIntent()
    data object HideIntTextFieldDialog : ConfigureIntent()
}