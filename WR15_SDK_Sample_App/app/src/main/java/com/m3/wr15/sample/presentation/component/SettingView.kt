package com.m3.wr15.sample.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingView(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    visible: Boolean = true,
    onClick: () -> Unit
) {
    if (visible) {
        TwoLineText(
            modifier = modifier.fillMaxWidth(),
            textModifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            title = title,
            value = value,
            onClick = onClick
        )
    }
}
