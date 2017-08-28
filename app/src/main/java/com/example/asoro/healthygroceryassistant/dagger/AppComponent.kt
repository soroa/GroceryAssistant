package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.managers.FavoritesManager
import com.example.asoro.healthygroceryassistant.managers.ShoppingListManager
import com.example.asoro.healthygroceryassistant.ui.favorites.FavoritesViewModel
import com.example.asoro.healthygroceryassistant.ui.intermittent_fasting.IntermittentFastingViewModel
import com.example.asoro.healthygroceryassistant.ui.recipe_detail.RecipeDetailViewModel
import com.example.asoro.healthygroceryassistant.ui.search.SearchViewModel
import com.example.asoro.healthygroceryassistant.ui.shopping_list.ShoppingListViewModel
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(RecipesRepoModule::class, DatabaseModule::class, ManagersModule::class))
interface AppComponent {
    fun inject(searchViewModel: SearchViewModel)

    fun inject(favoritesViewModel: FavoritesViewModel)

    fun inject(shoppingListViewModel: ShoppingListViewModel)

    fun inject(recipeDetailViewModel: RecipeDetailViewModel)

    fun inject(intermittentFastingViewModel: IntermittentFastingViewModel)

    fun inject(favoritesManager: FavoritesManager)

    fun inject(shoppingListManager: ShoppingListManager)

}