package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.ui.search.SearchViewModel
import dagger.Component


@ApplicationScope
@Component(modules = arrayOf(RecipesRepoModule::class))
interface RepositoryComponent {

    fun inject(searchViewModel: SearchViewModel)

}