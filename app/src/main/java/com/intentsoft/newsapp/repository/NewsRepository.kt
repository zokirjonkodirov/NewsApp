package com.intentsoft.newsapp.repository

import com.intentsoft.newsapp.api.RetrofitInstance
import com.intentsoft.newsapp.db.NewsDatabase
import com.intentsoft.newsapp.models.Article

/**
 * @author Zokirjon
 * @date 11/24/2021
 */
class NewsRepository(
    db: NewsDatabase
) {
    private val dao = db.getNewsDao()

    suspend fun getNews() =
        RetrofitInstance.api.getTopNews()

    suspend fun searchNews(searchQuery: String) =
        RetrofitInstance.api.searchNews(searchQuery)

    suspend fun insertNews(article: Article) = dao.upsert(article)

    suspend fun deleteNews(article: Article) = dao.deleteNews(article)

    fun getSavedNews() = dao.getAllArticles()
}