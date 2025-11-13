package com.m3.wr15.sample.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m3.wr15.sample.presentation.navigation.Routes

@Composable
fun MainView(padding: PaddingValues, onIntent: (MainIntent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    onIntent(MainIntent.NavigateTo(Routes.S2P_SCREEN))
                }
            ) {
                Text(text = "Connect Device by S2P")
            }

            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    onIntent(MainIntent.NavigateTo(Routes.SEARCH_SCREEN))
                }
            ) {
                Text(text = "Connect Device by Searching")
            }
        }
    }
}

@Preview
@Composable
fun MainViewPreview() {
    MainView(PaddingValues(), {})
}