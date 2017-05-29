package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.ui.search.SearchLifecycleFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        NetworkModule::class)
)
interface SearchComponent {
    fun inject(newsFragment: SearchLifecycleFragment)
}