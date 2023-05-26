package com.adiluhung.newsapp.data.remote.retrofit

import com.adiluhung.newsApp.BuildConfig
import com.adiluhung.newsapp.data.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    @GET("news?apikey=$API_KEY&q=teknologi")
    fun getNews(): Call<NewsResponse>
}