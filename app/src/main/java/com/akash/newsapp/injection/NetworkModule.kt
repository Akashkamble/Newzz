package com.akash.newsapp.injection

import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.repositories.NewsRepositoryImpl
import com.akash.newsapp.network.NewsApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Akash on 2020-01-14
 */

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideNewsApiService() : NewsApiService = NewsApiService()

    @JvmStatic
    @Provides
    fun provideNewsRepo(apiService: NewsApiService) : NewsRepository = NewsRepositoryImpl(apiService)
}