package com.akash.newsapp.injection

import com.akash.newsapp.ui.ArticleFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Akash on 2020-01-14
 */

@Singleton
@Component(modules = [ArticleModule::class, ViewModelModule::class])
interface ArticleFragmentComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(): ArticleFragmentComponent
    }

    fun injectArticleFragment(fragment: ArticleFragment)
}
