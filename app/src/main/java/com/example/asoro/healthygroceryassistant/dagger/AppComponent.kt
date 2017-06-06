package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.ui.favorites.FavoritesViewModel
import com.example.asoro.healthygroceryassistant.ui.intermittent_fasting.IntermittentFastingViewModel
import com.example.asoro.healthygroceryassistant.ui.recipe_detail.RecipeDetailViewModel
import com.example.asoro.healthygroceryassistant.ui.search.SearchViewModel
import dagger.Component


@ApplicationScope
@Component(modules = arrayOf(RecipesRepoModule::class, DatabaseModule::class))
interface AppComponent {
    fun inject(searchViewModel: SearchViewModel)

    fun inject(favoritesViewModel: FavoritesViewModel)

    fun inject(recipeDetailViewModel: RecipeDetailViewModel)

    fun inject(intermittentFastingViewModel: IntermittentFastingViewModel)
}