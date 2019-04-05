package com.akash.newsapp.data.response

import androidx.room.*

@Entity(tableName = "Articles")
data class NewsArticle (
    @PrimaryKey
    val publishedAt: String,
    @Embedded(prefix = "news")
    val source: NewsSource,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
)