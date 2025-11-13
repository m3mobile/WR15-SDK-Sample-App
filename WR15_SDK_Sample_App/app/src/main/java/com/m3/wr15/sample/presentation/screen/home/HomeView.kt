package com.m3.wr15.sample.presentation.screen.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m3.wr15.sample.presentation.navigation.Routes


@Composable
fun HomeView(innerPadding: PaddingValues, uiState: HomeUiState, onIntent: (HomeIntent) -> Unit) {
    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    onIntent(HomeIntent.NavigateToNext(Routes.SETTING_SCREEN))
                }
        ) {
            Icon(
                modifier = Modifier.padding(5.dp),
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Device Name",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.deviceName
            )

            Text(
                text = "Device Mac",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = uiState.deviceMac
            )

            Text(
                text = "Device Serial Number",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = uiState.deviceSerialNum
            )

            Text(
                text = "Device Battery",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "${uiState.battery}%"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .weight(1f)
                    .border(1.dp, Color.Gray)
            ) {
                LazyColumn(
                    state = scrollState,
                ) {
                    itemsIndexed(uiState.decodingDataList) { _, item ->
                        ItemView(item)
                    }
                }
            }

            Text(text = "count: ${uiState.decodingDataList.size}")

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                onClick = {
                    onIntent(HomeIntent.Disconnect)
                }
            ) {
                Text(text = "DisConnect")
            }
        }
    }
}


@Composable
fun ItemView(msg: String) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Text(text = msg)
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Preview
@Composable
fun HomeViewPreview() {
    HomeView(
        innerPadding = PaddingValues(),
        uiState = HomeUiState(
            deviceName = "Sample Device",
            deviceMac = "00:11:22:33:44:55",
            deviceSerialNum = "SN123456789",
            battery = 85
        ),
        {}
    )
}