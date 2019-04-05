package com.akash.newsapp.injection

import androidx.room.Room
import com.akash.newsapp.viewmodels.NewsViewModel
import com.akash.newsapp.data.db.NewsRoomDataBase
import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.repositories.NewsRepositoryImpl
import com.akash.newsapp.network.NewsApiService
import com.akash.newsapp.viewmodels.GeneralViewModel
import com.akash.newsapp.viewmodels.BusinessViewModel
import com.akash.newsapp.viewmodels.TechnologyViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    // Room Database instance
    single {
        Room.databaseBuilder(androidApplication(), NewsRoomDataBase::class.java, "news_db")
            .build()
    }
    factory { NewsApiService() }
    factory { get<NewsRoomDataBase>().getNewsDao() }
    factory { NewsRepositoryImpl(get(), get()) as NewsRepository }
    viewModel { NewsViewModel(get()) }
    viewModel { GeneralViewModel(get()) }
    viewModel { BusinessViewModel(get()) }
    viewModel { TechnologyViewModel(get()) }
}