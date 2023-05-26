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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.adiluhung.newsapp.data.local.FavoriteNewsModel
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.ui.screen.favorite.FavoriteViewModel

@Composable
fun FavoriteNewsCard(
    modifier: Modifier = Modifier,
    news: FavoriteNewsModel,
    viewModel: FavoriteViewModel,
    navigateToDetail: (NewsItem) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ), shape = RoundedCornerShape(16.dp), modifier = modifier.padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.clickable {
            navigateToDetail(
                NewsItem(
                    imageUrl = news.imageUrl,
                    link = news.link,
                    description = news.description,
                    language = news.language,
                    title = news.title,
                    pubDate = news.pubDate,
                    content = news.content,
                    videoUrl = news.videoUrl,
                    sourceId = news.sourceId
                )
            )
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
