package com.adiluhung.newsapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.adiluhung.newsapp.data.local.FavoriteNewsDatabase
import com.adiluhung.newsapp.data.local.FavoriteNewsModel
import com.adiluhung.newsapp.data.remote.response.NewsItem
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteNewsRepository(context: Context) {
    private val database = FavoriteNewsDatabase.getDatabase(context)
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun addNewsToFavorite(newsItem: NewsItem) {
        executorService.execute {
            database.favoriteNewsDao().insertFavoriteNews(
                FavoriteNewsModel(
                    imageUrl = newsItem.imageUrl,
                    link = newsItem.link,
                    description = newsItem.description,
                    language = newsItem.language,
                    title = newsItem.title,
                    pubDate = newsItem.pubDate,
                    content = newsItem.content,
                    videoUrl = newsItem.videoUrl,
                    sourceId = newsItem.sourceId
                )
            )
        }
    }


    fun deleteFavoriteNewsAsNewsItem(newsItem: NewsItem) {
        executorService.execute {
            database.favoriteNewsDao().deleteFavoriteNews(
                newsItem.title
            )
        }
    }

    fun deleteFavoriteNewsAsFavoriteNewsModel(favoriteNewsModel: FavoriteNewsModel) {
        executorService.execute {
            database.favoriteNewsDao().deleteFavoriteNews(
                favoriteNewsModel.title
            )
        }
    }

    fun checkIfNewsAddedToFavorite(title: String): LiveData<Boolean> =
        database.favoriteNewsDao().checkIfNewsAddedToFavorite(title)

    fun getAllFavoriteNews(): LiveData<List<FavoriteNewsModel>> = database.favoriteNewsDao().getAllFavoriteNews()

}