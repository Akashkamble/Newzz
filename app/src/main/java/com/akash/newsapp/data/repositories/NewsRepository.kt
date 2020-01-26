package com.akash.newsapp.data.repositories

import com.akash.newsapp.data.response.NewsResponse
import com.akash.newsapp.internals.Result

interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String): Result<NewsResponse>
}