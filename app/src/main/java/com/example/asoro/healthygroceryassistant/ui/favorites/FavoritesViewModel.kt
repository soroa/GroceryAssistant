package com.example.asoro.healthygroceryassistant.ui.favorites

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.RecipeWithIngredients
import com.example.asoro.healthygroceryassistant.managers.FavoritesManager
import com.example.asoro.healthygroceryassistant.managers.ShoppingListManager
import com.example.asoro.healthygroceryassistant.model.Recipe
import javax.inject.Inject

class FavoritesViewModel:ViewModel() {

    @Inject
    lateinit var favoriteManager: FavoritesManager
    @Inject
    lateinit var shoppingListManager: ShoppingListManager

    init {
        MyApp.sAppComponent.inject(this)
    }

    fun removeFromFavorites(recipe: Recipe) {
        favoriteManager.removeFromFavorites(recipe)
    }

    fun getFavorites(): LiveData<List<RecipeWithIngredients>> {
        return favoriteManager.getFavorites()
    }

    fun addRecipeToShoppingList(recipe:Recipe){
        shoppingListManager.addRecipeToShoppingList(recipe)
    }

    fun removeRecipeFromShoppingList(recipe:Recipe){
        shoppingListManager.removeRecipeFromShoppingList(recipe)
    }

}