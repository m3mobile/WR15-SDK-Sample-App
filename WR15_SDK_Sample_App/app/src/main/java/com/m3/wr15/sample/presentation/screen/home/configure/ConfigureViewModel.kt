package com.m3.wr15.sample.presentation.screen.home.configure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m3.wr15.sample.domain.BtDeviceManager
import com.m3.wr15.sdk.api.SettingCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ConfigureViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConfigureUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<ConfigureEvent>()
    val event = _event.asSharedFlow()

    init {
        observeDeviceState()
    }

    fun handleIntent(intent: ConfigureIntent) {
        when (intent) {
            is ConfigureIntent.ShowRadioDialog -> {
                showRadioDialog(intent)
            }

            is ConfigureIntent.HideRadioDialog -> {
                hideRadioDialog()
            }

            is ConfigureIntent.ShowStrTextFieldDialog -> {
                showStrTextFieldDialog(intent)
            }

            is ConfigureIntent.ChangeSetting -> {
                changeSetting(intent.settingCommand)
            }

            is ConfigureIntent.HideStrTextFieldDialog -> {
                hideStrTextFieldDialog()
            }

            is ConfigureIntent.ShowIntTextFieldDialog -> {
                showIntTextFieldDialog(intent)
            }

            is ConfigureIntent.HideIntTextFieldDialog -> {
                hideIntTextFieldDialog()
            }
        }
    }

    private fun showRadioDialog(intent: ConfigureIntent.ShowRadioDialog) {
        _uiState.update {
            it.copy(
                radioDialogState = RadioDialogUiState(
                    isVisible = true,
                    title = intent.title,
                    options = intent.options,
                    selectedOption = intent.selectedOption,
                    onOptionSelected = intent.onOptionSelected,
                    onDismiss = intent.onDismiss
                )
            )
        }
    }

    private fun showStrTextFieldDialog(intent: ConfigureIntent.ShowStrTextFieldDialog) {
        _uiState.update {
            it.copy(
                strTextFiledDialogState = StrTextFieldDialogUiState(
                    isVisible = true,
                    title = intent.title,
                    subTitle = intent.subtitle,
                    currentValue = intent.currentValue,
                    onConfirm = intent.onConfirm,
                    onDismiss = intent.onDismiss
                )
            )
        }
    }

    private fun showIntTextFieldDialog(intent: ConfigureIntent.ShowIntTextFieldDialog) {
        _uiState.update {
            it.copy(
                intTextFiledDialogState = IntTextFieldDialogUiState(
                    isVisible = true,
                    title = intent.title,
                    currentValue = intent.currentValue,
                    onConfirm = intent.onConfirm,
                    onDismiss = intent.onDismiss
                )
            )
        }
    }

    private fun hideRadioDialog() {
        _uiState.update {
            it.copy(
                radioDialogState = RadioDialogUiState()
            )
        }
    }

    private fun hideStrTextFieldDialog() {
        _uiState.update {
            it.copy(
                strTextFiledDialogState = StrTextFieldDialogUiState()
            )
        }
    }

    private fun hideIntTextFieldDialog() {
        _uiState.update {
            it.copy(
                intTextFiledDialogState = IntTextFieldDialogUiState()
            )
        }
    }

    private fun changeSetting(settingCommand: SettingCommand) {
        BtDeviceManager.changeSetting(settingCommand)
    }

    private fun observeDeviceState() {
        BtDeviceManager.deviceState
            .onEach { deviceState ->
                _uiState.update { it.copy(deviceState = deviceState) }
            }
            .launchIn(viewModelScope)

        BtDeviceManager.isConnected
            .onEach { isConnected ->
                if (!isConnected) {
                    _event.emit(ConfigureEvent.NavigateToHome)
                    resetState()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun resetState() {
        _uiState.value = ConfigureUiState()
    }
}