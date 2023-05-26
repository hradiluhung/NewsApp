package com.adiluhung.newsapp.ui.common

import com.adiluhung.newsapp.data.remote.response.NewsItem

sealed class UiState<out T: Any?> {

    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: List<NewsItem>?) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}