package com.m3.wr15.sample.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingCheckBox(
    title: String,
    isChecked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    var lastCheckedChange by remember { mutableLongStateOf(0L) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = title
        )
        Box(modifier = Modifier.weight(1f))
        Checkbox(
            enabled = enabled,
            checked = isChecked,
            onCheckedChange = { newChecked ->
                val currentTime = System.currentTimeMillis()
                // Throttle to prevent rapid toggling
                if (currentTime - lastCheckedChange > 1000) {
                    lastCheckedChange = currentTime
                    onCheckedChange(newChecked)
                }
            }
        )
    }
}