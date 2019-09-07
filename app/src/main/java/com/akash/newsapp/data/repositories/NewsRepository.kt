package com.akash.newsapp.data.repositories

import com.akash.newsapp.data.response.NewsResponse
import kotlinx.coroutines.Deferred

interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String): Deferred<NewsResponse>
}