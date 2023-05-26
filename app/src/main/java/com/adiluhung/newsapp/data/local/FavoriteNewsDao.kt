package com.adiluhung.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteNewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteNews(news: FavoriteNewsModel)

    @Query("SELECT * FROM favorite_news")
    fun getAllFavoriteNews(): LiveData<List<FavoriteNewsModel>>

    @Query("DELETE FROM favorite_news WHERE title=:title")
    fun deleteFavoriteNews(title: String)

    @Query("SELECT EXISTS(SELECT * FROM favorite_news WHERE title=(:title))")
    fun checkIfNewsAddedToFavorite(title: String): LiveData<Boolean>
}