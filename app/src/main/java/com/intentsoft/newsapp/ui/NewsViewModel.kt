package com.intentsoft.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intentsoft.newsapp.models.Article
import com.intentsoft.newsapp.models.NewsResponse
import com.intentsoft.newsapp.repository.NewsRepository
import com.intentsoft.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * @author Zokirjon
 * @date 11/27/2021
 */
class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {

    val news = MutableLiveData<Resource<NewsResponse>>()
    val searchNews = MutableLiveData<Resource<NewsResponse>>()

    fun getNews() = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = newsRepository.getNews()
        news.postValue(handleNews(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery)
        searchNews.postValue(handleSearches(response))
    }

    private fun handleNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.code().toString())
    }

    private fun handleSearches(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.code().toString())
    }

    fun saveNews(article: Article) = viewModelScope.launch {
        newsRepository.insertNews(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

}