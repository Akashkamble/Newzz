package com.akash.newsapp.data.repositories

import com.akash.newsapp.data.response.NewsResponse
import com.akash.newsapp.network.NewsApiService
import kotlinx.coroutines.Deferred

class NewsRepositoryImpl constructor(private val newsApiService: NewsApiService) : NewsRepository {

    override suspend fun getArticlesByCategoryAsync(category: String): Deferred<NewsResponse> {
        return newsApiService.getArticlesByCateGoryAsync(category)
    }
}