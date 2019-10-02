package com.akash.newsapp.data.repositories

import com.akash.newsapp.data.response.NewsResponse

interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String): NewsResponse
}