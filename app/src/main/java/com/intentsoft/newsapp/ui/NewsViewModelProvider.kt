package com.intentsoft.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intentsoft.newsapp.repository.NewsRepository

/**
 * @author Zokirjon
 * @date 11/27/2021
 */
class NewsViewModelProvider(
    private val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}