package com.adiluhung.newsapp.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.newsApp.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = "Hakam Royhan Adiluhung", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "hakamra107@gmail.com")
    }
}