package com.m3.wr15.sample.presentation.screen.s2p

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m3.wr15.sample.R

@Composable
fun S2PView(innerPadding: PaddingValues, uiState: S2PUiState) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Scan the barcode below to set the WR15 to SPP mode",
                textAlign = TextAlign.Center
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                painter = painterResource(R.drawable.img_barcode_spp_mode),
                contentScale = ContentScale.Fit,
                contentDescription = "S2P Barcode"
            )

            Text(
                modifier = Modifier.fillMaxWidth().padding(top=50.dp),
                text = "Scan the barcode below with your WR15 to connect",
                textAlign = TextAlign.Center
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                bitmap = uiState.s2pBarcode?.asImageBitmap() ?: ImageBitmap(1, 1),
                contentScale = ContentScale.FillWidth,
                contentDescription = "S2P Barcode"
            )
        }
    }
}

@Preview
@Composable
fun S2PViewPreview() {
    S2PView(PaddingValues(), S2PUiState())
}