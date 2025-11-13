package com.m3.wr15.sample.presentation.screen.home.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m3.wr15.sample.presentation.component.SettingCheckBox
import com.m3.wr15.sample.presentation.component.SettingView
import com.m3.wr15.sample.presentation.component.TitleText
import com.m3.wr15.sample.presentation.model.EndCharRadioVal
import com.m3.wr15.sample.presentation.model.SoundRadioVal
import com.m3.wr15.sdk.api.Settings
import com.m3.wr15.sdk.api.model.basic.BasicFormatType
import com.m3.wr15.sdk.api.model.general.SoundType

@Composable
fun ConfigureView(innerPadding: PaddingValues, uiState: ConfigureUiState, onIntent: (ConfigureIntent) -> Unit) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TitleText(
                modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                "General Settings"
            )

            // Sound
            SettingView(
                title = "Sound",
                value = SoundRadioVal.fromBarcodeValue(uiState.deviceState.general.sound)?.label ?: "null",
                onClick = {
                    onIntent(
                        ConfigureIntent.ShowRadioDialog(
                            title = "Sound",
                            options = SoundRadioVal.getList(),
                            selectedOption = SoundRadioVal.fromBarcodeValue(uiState.deviceState.general.sound),
                            onOptionSelected = { selectedOption ->
                                onIntent(
                                    ConfigureIntent.ChangeSetting(
                                        Settings.General.setSound(selectedOption as SoundType)
                                    )
                                )
                            },
                            onDismiss = { onIntent(ConfigureIntent.HideRadioDialog) }
                        )
                    )
                }
            )

            // Vibration
            if (uiState.deviceState.general.vibrateEnabled != null) {
                SettingCheckBox(
                    title = "Vibration",
                    isChecked = uiState.deviceState.general.vibrateEnabled!!,
                ) { isChecked ->
                    onIntent(ConfigureIntent.ChangeSetting(Settings.General.setVibrate(isChecked)))
                }
            }

            // Aimer
            if (uiState.deviceState.general.aimEnabled != null) {
                SettingCheckBox(
                    title = "Aimer",
                    isChecked = uiState.deviceState.general.aimEnabled!!,
                ) { isChecked ->
                    onIntent(ConfigureIntent.ChangeSetting(Settings.General.setAimer(isChecked)))
                }
            }

            // Illumination
            if (uiState.deviceState.general.illuminationEnabled != null) {
                SettingCheckBox(
                    title = "Illumination",
                    isChecked = uiState.deviceState.general.illuminationEnabled!!,
                ) { isChecked ->
                    onIntent(ConfigureIntent.ChangeSetting(Settings.General.setIllumination(isChecked)))
                }
            }

            HorizontalDivider()

            TitleText(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                "Basic data formatting"
            )

            // Prefix
            SettingView(
                title = "Prefix",
                value = uiState.deviceState.basicFormat.prefix,
                onClick = {
                    onIntent(
                        ConfigureIntent.ShowStrTextFieldDialog(
                            title = "Prefix",
                            currentValue = uiState.deviceState.basicFormat.prefix,
                            onConfirm = { value ->
                                onIntent(ConfigureIntent.ChangeSetting(Settings.BasicDataFormat.setPrefix(value)))
                            },
                            onDismiss = {
                                onIntent(ConfigureIntent.HideStrTextFieldDialog)
                            }
                        )
                    )
                }
            )

            // Postfix
            SettingView(
                title = "Postfix",
                value = uiState.deviceState.basicFormat.postfix,
                onClick = {
                    onIntent(
                        ConfigureIntent.ShowStrTextFieldDialog(
                            title = "Postfix",
                            currentValue = uiState.deviceState.basicFormat.postfix,
                            onConfirm = { value ->
                                onIntent(ConfigureIntent.ChangeSetting(Settings.BasicDataFormat.setPostfix(value)))
                            },
                            onDismiss = {
                                onIntent(ConfigureIntent.HideStrTextFieldDialog)
                            }
                        )
                    )
                }
            )

            // End Character
            if (uiState.deviceState.basicFormat.endChar != null) {
                SettingView(
                    title = "End Character",
                    value = EndCharRadioVal.fromBarcodeValue(uiState.deviceState.basicFormat.endChar)?.label ?: "null",
                ) {
                    onIntent(
                        ConfigureIntent.ShowRadioDialog(
                            title = "End Character",
                            options = EndCharRadioVal.getList(),
                            selectedOption = EndCharRadioVal.fromBarcodeValue(uiState.deviceState.basicFormat.endChar),
                            onOptionSelected = { selectedOption ->
                                onIntent(
                                    ConfigureIntent.ChangeSetting(
                                        Settings.BasicDataFormat.setEndChar(selectedOption as BasicFormatType.EndCharType)
                                    )
                                )
                            },
                            onDismiss = { onIntent(ConfigureIntent.HideRadioDialog) }
                        )
                    )
                }
            }

            HorizontalDivider()

            TitleText(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                text = "Reader Params"
            )

            if (uiState.deviceState.readerSettings.laserOnTime != null) {
                SettingView(
                    title = "Laser On Time(1~10S)",
                    value = uiState.deviceState.readerSettings.laserOnTime?.div(10).toString(),
                ) {
                    onIntent(
                        ConfigureIntent.ShowIntTextFieldDialog(
                        title = "Laser On Time(1~10S)",
                        currentValue = uiState.deviceState.readerSettings.laserOnTime!!.div(10),
                        onConfirm = { value ->
                            onIntent(
                                ConfigureIntent.ChangeSetting(Settings.ReaderParams.setLaserOnTime(value * 10))
                            )
                        },
                        onDismiss = {
                            onIntent(ConfigureIntent.HideIntTextFieldDialog)
                        }
                    ))
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingViewPreview() {
    ConfigureView(PaddingValues(), ConfigureUiState(), {})
}