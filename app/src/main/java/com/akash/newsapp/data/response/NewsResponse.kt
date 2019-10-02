package com.akash.newsapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<NewsArticle>,

    @SerializedName("status")
    var status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)