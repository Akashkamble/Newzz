package com.akash.newsapp.injection

import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.repositories.NewsRepositoryImpl
import com.akash.newsapp.data.network.NewsApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Akash on 2020-01-14
 */

@Module
object ArticleModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideNewsApiService() : NewsApiService = NewsApiService()

    @JvmStatic
    @Provides
    @Singleton
    fun provideNewsRepo(
        apiService: NewsApiService,
        moshi: Moshi
    ) : NewsRepository = NewsRepositoryImpl(apiService,moshi)

    @JvmStatic
    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder().build()
}