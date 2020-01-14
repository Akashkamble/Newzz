package com.akash.newsapp.injection

import com.akash.newsapp.ui.ArticleFragment
import dagger.Subcomponent

/**
 * Created by Akash on 2020-01-14
 */

@Subcomponent
interface ArticleComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ArticleComponent
    }

    fun inject(fragment: ArticleFragment)
}