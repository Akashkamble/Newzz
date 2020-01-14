package com.akash.newsapp.injection

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Akash on 2020-01-14
 */

@Singleton
@Component(modules = [NetworkModule::class, AppSubComponent::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun articleComponent(): ArticleComponent.Factory
}