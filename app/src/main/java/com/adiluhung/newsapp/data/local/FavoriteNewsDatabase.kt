package com.adiluhung.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteNewsModel::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteNewsDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteNewsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteNewsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteNewsDatabase::class.java, "favorite_news_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}