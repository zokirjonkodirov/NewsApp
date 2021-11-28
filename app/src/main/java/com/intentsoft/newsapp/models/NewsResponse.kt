package com.intentsoft.newsapp.models

/**
 * @author Zokirjon
 * @date 11/24/2021
 */
data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)