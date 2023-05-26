package com.adiluhung.newsapp.ui.screen.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.ui.ViewModelFactory
import com.adiluhung.newsapp.ui.components.FavoriteNewsCard

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(repository = FavoriteNewsRepository(context = LocalContext.current))
    ),
    navigateToDetail: (NewsItem) -> Unit
) {
    val listFavoriteNews = viewModel.getAllFavorites().observeAsState().value

    if (listFavoriteNews != null) {
        LazyColumn(contentPadding = PaddingValues(16.dp), modifier = modifier) {
            items(listFavoriteNews) { data ->
                FavoriteNewsCard(
                    news = data,
                    viewModel = viewModel,
                    navigateToDetail = navigateToDetail
                )
            }
        }
        if (listFavoriteNews.isEmpty()) {
            Text(
                "No favorite news",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic,
            )
        }
    }
}