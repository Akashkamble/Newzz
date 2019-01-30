package com.akash.newsapp.data.repositories

import com.akash.newsapp.data.response.NewsArticle
import com.akash.newsapp.data.response.NewsResponse
import kotlinx.coroutines.Deferred

interface NewsRepository {
    suspend fun getRemoteArticles() : Deferred<NewsResponse>
    suspend fun getLocalArticles() : List<NewsArticle>
    fun saveArticles(newsArticles: List<NewsArticle>)
    suspend fun getArticlesByCategoryAsync(category : String) : Deferred<NewsResponse>
}