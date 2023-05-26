package com.adiluhung.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.ui.screen.detail.DetailScreen
import com.adiluhung.newsapp.ui.theme.NewsTheme

class DetailActivity : ComponentActivity() {

    private val news: NewsItem by lazy {
        intent?.getSerializableExtra(NEWS_ID) as NewsItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen(news = news, onBackClick = {
                        finish()
                    })
                }
            }
        }
    }

    companion object {
        const val NEWS_ID = "news_id"
    }
}