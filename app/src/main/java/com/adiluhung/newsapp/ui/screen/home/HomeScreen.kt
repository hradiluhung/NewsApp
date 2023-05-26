package com.adiluhung.newsapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adiluhung.newsApp.R
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.ui.ViewModelFactory
import com.adiluhung.newsapp.ui.common.UiState
import com.adiluhung.newsapp.ui.components.NewsCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(repository = FavoriteNewsRepository(context = LocalContext.current))
    ),
    navigateToDetail: (NewsItem) -> Unit,
) {
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
                viewModel.getAllNews()
            }

            is UiState.Success -> {
                HomeContent(
                    modifier = modifier,
                    listNews = uiState.data,
                    navigateToDetail = navigateToDetail,
                    viewModel = viewModel,
                )
            }

            is UiState.Error -> {
                Log.d("HomeScreen", uiState.errorMessage)
            }
        }
    }
}

@Composable
fun HomeContent(
    listNews: List<NewsItem>?,
    modifier: Modifier = Modifier,
    navigateToDetail: (NewsItem) -> Unit,
    viewModel: HomeViewModel
) {
    var query by remember { mutableStateOf("") }
    var listNewsState by remember { mutableStateOf(listNews) }

    Column {
        SearchBar(
            query = query,
            onQueryChange = { newQuery ->
                query = newQuery
                listNewsState = listNews?.filter { it.title.contains(query, ignoreCase = true) }
            },
        )
        if (listNewsState?.size!! > 0) {
            LazyColumn(contentPadding = PaddingValues(16.dp), modifier = modifier) {
                items(listNewsState as List) { data ->
                    NewsCard(
                        news = data,
                        navigateToDetail = navigateToDetail,
                        viewModel = viewModel
                    )
                }
            }
        } else {
            Text(
                "No news found",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic,
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(stringResource(R.string.search_news))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
    )
}