package com.m3.wr15.sample.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwoLineText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    title: String,
    value: String? = null,
    bgColor: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(bgColor)
            .clickable { onClick() }) {
        Column(
            modifier = textModifier
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )

            if (value != null) {
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    text = value,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TwoLineTextPreView() {
    TwoLineText(
        modifier = Modifier.background(Color.White),
        title = "Title",
        value = "Value",
    )
}