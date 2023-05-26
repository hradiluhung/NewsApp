package com.adiluhung.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.ui.screen.detail.DetailViewModel
import com.adiluhung.newsapp.ui.screen.favorite.FavoriteViewModel
import com.adiluhung.newsapp.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: FavoriteNewsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}