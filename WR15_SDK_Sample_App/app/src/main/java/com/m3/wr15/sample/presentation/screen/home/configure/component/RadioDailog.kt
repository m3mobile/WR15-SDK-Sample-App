package com.m3.wr15.sample.presentation.screen.home.configure.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.m3.wr15.sample.presentation.model.SettingRadioValue
import com.m3.wr15.sdk.api.model.TypeValue

@Composable
fun RadioDialogView(
    title: String,
    options: List<SettingRadioValue>,
    selectedOption: SettingRadioValue?,
    onOptionSelected: (TypeValue) -> Unit,
    onDismiss: () -> Unit
) {
    val scrollState = rememberScrollState()
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            Column(
                modifier = Modifier
                    .selectableGroup()
                    .verticalScroll(scrollState)
            ) {
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (option == selectedOption),
                                onClick = {
                                    onOptionSelected(option.value)
                                    onDismiss()
                                }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            modifier = Modifier.minimumInteractiveComponentSize(),
                            selected = (option == selectedOption),
                            onClick = null
                        )
                        Text(
                            text = option.label,
                            modifier = Modifier.padding(start = 8.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        },
        confirmButton = { /* Nothing */ },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancel",
                    color = Color.Black
                )
            }
        }
    )
}