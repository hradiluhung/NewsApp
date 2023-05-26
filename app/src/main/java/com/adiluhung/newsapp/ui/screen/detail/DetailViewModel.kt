package com.adiluhung.newsapp.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.data.remote.response.NewsItem

class DetailViewModel(private val repository: FavoriteNewsRepository) : ViewModel() {
    fun addToFavorite(newsItem: NewsItem) {
        repository.addNewsToFavorite(newsItem)
    }

    fun deleteFromFavorite(newsItem: NewsItem) {
        repository.deleteFavoriteNewsAsNewsItem(newsItem)
    }

    fun checkIfNewsAddedToFavorite(title: String): LiveData<Boolean> =
        repository.checkIfNewsAddedToFavorite(title)

}