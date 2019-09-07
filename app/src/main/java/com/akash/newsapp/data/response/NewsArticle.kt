package com.akash.newsapp.data.response

data class NewsArticle(
    val publishedAt: String,
    val source: NewsSource,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?
)