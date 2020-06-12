package com.akash.newsapp.data.repositories

import com.akash.newsapp.base.Result
import com.akash.newsapp.data.response.NewsResponse

interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String, page: Int): Result<NewsResponse>
}
