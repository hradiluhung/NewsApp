package com.adiluhung.newsapp.ui.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.data.local.FavoriteNewsModel

class FavoriteViewModel(private val repository: FavoriteNewsRepository) : ViewModel() {
    fun getAllFavorites(): LiveData<List<FavoriteNewsModel>> = repository.getAllFavoriteNews()

    fun deleteFromFavorite(newsItem: FavoriteNewsModel) {
        repository.deleteFavoriteNewsAsFavoriteNewsModel(newsItem)
    }
}