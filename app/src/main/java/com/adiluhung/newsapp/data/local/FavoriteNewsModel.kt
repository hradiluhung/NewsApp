package com.adiluhung.newsapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_news")
@Parcelize
data class FavoriteNewsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "pubDate")
    val pubDate: String,

    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "video_url")
    val videoUrl: String?,

    @ColumnInfo(name = "source_id")
    val sourceId: String,
): Parcelable