package com.adiluhung.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("nextPage")
    val nextPage: String,

    @field:SerializedName("results")
    val results: List<NewsItem>,

    @field:SerializedName("status")
    val status: String
)

data class NewsItem(

    @field:SerializedName("country")
    val country: List<String>? = null,

    @field:SerializedName("creator")
    val creator: List<String>? = null,

    @field:SerializedName("keywords")
    val keywords: List<String>? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("language")
    val language: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("pubDate")
    val pubDate: String,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("video_url")
    val videoUrl: String? = null,

    @field:SerializedName("source_id")
    val sourceId: String,

    @field:SerializedName("category")
    val category: List<String>? = null
) : Serializable
