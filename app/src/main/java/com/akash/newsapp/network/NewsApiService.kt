package com.akash.newsapp.network

import com.akash.newsapp.data.response.NewsResponse
import com.akash.newsapp.internals.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {


    @GET("top-headlines?sortBy=publishedAt&pageSize=100")
    suspend fun getArticlesByCateGoryAsync(
        @Query("category") category: String,
        @Query("country") country: String = Constants().COUNTRY
    ): NewsResponse


    companion object {
        operator fun invoke(): NewsApiService {
            val requestInterceptor = Interceptor { chain ->
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
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
}
