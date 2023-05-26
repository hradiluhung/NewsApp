package com.adiluhung.newsapp.ui.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.adiluhung.newsApp.R
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.ui.ViewModelFactory

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    news: NewsItem,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(repository = FavoriteNewsRepository(context = LocalContext.current))
    )
) {
    val context = LocalContext.current
    val isFavorite = viewModel.checkIfNewsAddedToFavorite(news.title).observeAsState().value

    Column(modifier = modifier) {
        Box {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() })
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (news.imageUrl != null) {
                AsyncImage(
                    model = news.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                )
            }
            Text(
                text = news.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.published_at, news.pubDate),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row() {
                Button(
                    onClick = {
                        context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(news.link)))
                    },
                    modifier = Modifier.padding(bottom = 24.dp),
                ) {
                    Text(stringResource(R.string.detail_link))
                }
                Spacer(modifier = Modifier.width(8.dp))
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

            news.content?.let {text ->
                Text(
                    text = text, textAlign = TextAlign.Justify
                )
            }

        }
    }
}