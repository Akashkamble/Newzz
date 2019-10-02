package com.akash.newsapp.injection

import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.repositories.NewsRepositoryImpl
import com.akash.newsapp.network.NewsApiService
import com.akash.newsapp.viewmodels.ArticleViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {

    factory { NewsApiService() }
    factory { NewsRepositoryImpl(get()) as NewsRepository }
    viewModel { ArticleViewModel(get()) }
}