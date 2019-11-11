package com.akash.newsapp.data.response

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class NewsResponse(
    val articles: List<NewsArticle>,

    var status: String,

    val totalResults: Int
)
