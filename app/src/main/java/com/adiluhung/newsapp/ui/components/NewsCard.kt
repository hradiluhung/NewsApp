package com.adiluhung.newsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.adiluhung.newsApp.R
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.ui.screen.home.HomeViewModel

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    news: NewsItem,
    navigateToDetail: (NewsItem) -> Unit,
    viewModel: HomeViewModel
) {
    val isFavorite = viewModel.checkIfNewsAddedToFavorite(news.title).observeAsState().value

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ), shape = RoundedCornerShape(16.dp), modifier = modifier.padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.clickable {
            navigateToDetail(news)
        }) {
            if (news.imageUrl != null) {
                AsyncImage(
                    model = news.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp)),
                )
            }
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = news.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.published_at, news.pubDate),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontStyle = FontStyle.Italic
                        )
                    )
                }

                Spacer(modifier = modifier.width(16.dp))


                if (isFavorite == false) {
                    OutlinedButton(onClick = { viewModel.addToFavorite(news) }) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Add to favorite"
                        )
                    }
                } else {
                    Button(onClick = { viewModel.deleteFromFavorite(news) }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Remove from favorite"
                        )
                    }

                }

            }

        }
    }
}
