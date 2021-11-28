package com.intentsoft.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.intentsoft.newsapp.models.Article

/**
 * @author Zokirjon
 * @date 11/27/2021
 */
@Database(
    entities = [Article::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(NewsTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        @Volatile
        private var instance: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_db.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}