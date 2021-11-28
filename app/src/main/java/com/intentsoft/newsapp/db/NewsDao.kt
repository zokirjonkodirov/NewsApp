package com.intentsoft.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.intentsoft.newsapp.models.Article

/**
 * @author Zokirjon
 * @date 11/27/2021
 */
@Dao
interface NewsDao {

    @JvmSuppressWildcards
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @JvmSuppressWildcards
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @JvmSuppressWildcards
    @Delete
    suspend fun deleteNews(article: Article)
}