package com.m3.wr15.sample.presentation.screen.home.configure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun StrTextFieldDialog(
    title: String,
    subTitle: String? = null,
    currentValue: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var inputValue by remember { mutableStateOf(currentValue) }

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(text = title)

                if (subTitle != null) {
                    Text(
                        text = subTitle,
                        fontSize = 14.sp
                    )
                }
            }
        },
        text = {
            TextField(
                modifier = Modifier.background(Color.White),
                value = inputValue,
                onValueChange = { newValue ->
                    inputValue = newValue
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                )
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(inputValue)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
private fun TextFieldDialogPreview() {
    StrTextFieldDialog("title", "subtitle", "currentValue", onConfirm = {}, onDismiss = {})
}