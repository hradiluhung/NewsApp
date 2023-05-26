package com.adiluhung.newsapp.ui.screen.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adiluhung.newsapp.data.FavoriteNewsRepository
import com.adiluhung.newsapp.data.remote.response.NewsItem
import com.adiluhung.newsapp.data.remote.response.NewsResponse
import com.adiluhung.newsapp.data.remote.retrofit.ApiConfig
import com.adiluhung.newsapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: FavoriteNewsRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<NewsItem>>>()
    val uiState: LiveData<UiState<List<NewsItem>>> = _uiState

    fun addToFavorite(newsItem: NewsItem) {
        repository.addNewsToFavorite(newsItem)
    }

    fun deleteFromFavorite(newsItem: NewsItem) {
        repository.deleteFavoriteNewsAsNewsItem(newsItem)
    }

    fun checkIfNewsAddedToFavorite(title: String): LiveData<Boolean> =
        repository.checkIfNewsAddedToFavorite(title)

    fun getAllNews() {
        val client = ApiConfig.getApiService().getNews()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val result = responseBody?.results
                    _uiState.value = UiState.Success(result)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private val TAG = FavoriteNewsRepository::class.java.simpleName
    }
}