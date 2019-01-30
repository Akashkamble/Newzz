package com.akash.newsapp.data.response

data class NewsResponse(
    val articles: List<NewsArticle>,
    var status: String,
    val totalResults: Int
)