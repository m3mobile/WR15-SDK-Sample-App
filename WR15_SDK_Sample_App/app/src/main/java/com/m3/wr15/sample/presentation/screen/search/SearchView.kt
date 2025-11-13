package com.m3.wr15.sample.presentation.screen.search

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

@Composable
fun SearchView(innerPadding: PaddingValues, uiState: SearchUiState, onIntent: (SearchIntent) -> Unit) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val connOk = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(innerPadding)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                text = "Searching...",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .border(1.dp, Color.Gray)
            ) {
                itemsIndexed(uiState.foundBtDevices) { index, device ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                            .clickable {
                                onIntent(SearchIntent.StopDiscoveryScan)
                                onIntent(SearchIntent.ConnectToDevice(device))
                            },
                        text = "${device.name} (${device.address})",
                        fontSize = 16.sp
                    )

                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchViewPreview() {
    SearchView(PaddingValues(), SearchUiState(), {})
}