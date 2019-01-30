package com.akash.newsapp.network

import com.akash.newsapp.data.response.NewsResponse
import com.akash.newsapp.internals.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines?sortBy=publishedAt")
    fun getHeadLines(
        @Query("country") country: String
    ): Deferred<NewsResponse>

    @GET("top-headlines?sortBy=publishedAt")
    fun getArticlesByCateGory(
        @Query("category") category : String,
        @Query("country") country: String = Constants().COUNTRY
    ): Deferred<NewsResponse>


    companion object {
        operator fun invoke(): NewsApiService {
            val requestInteceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", Constants().API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInteceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v2/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
}