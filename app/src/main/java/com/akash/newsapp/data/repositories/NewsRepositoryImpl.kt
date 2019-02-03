package com.akash.newsapp.data.repositories

import com.akash.newsapp.data.db.NewsDao
import com.akash.newsapp.data.response.NewsArticle
import com.akash.newsapp.data.response.NewsResponse
import com.akash.newsapp.network.NewsApiService
import kotlinx.coroutines.Deferred

class NewsRepositoryImpl constructor(private val newsApiService: NewsApiService,
                                     private val newsDao: NewsDao) : NewsRepository {
    override fun saveArticles(newsArticles: List<NewsArticle>) {
        newsDao.insertAll(newsArticles)
    }

    override suspend fun getArticlesByCategoryAsync(category : String): Deferred<NewsResponse> {
        return newsApiService.getArticlesByCateGoryAsync(category)
    }
}