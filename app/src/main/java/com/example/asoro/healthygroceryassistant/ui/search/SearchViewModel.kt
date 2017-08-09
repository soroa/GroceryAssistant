package com.example.asoro.healthygroceryassistant.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.RecipeWithIngredients
import com.example.asoro.healthygroceryassistant.managers.FavoritesManager
import com.example.asoro.healthygroceryassistant.managers.ShoppingListManager
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.example.asoro.healthygroceryassistant.remote.RecipesRepo
import javax.inject.Inject

class SearchViewModel : ViewModel() {

    @Inject
    lateinit var recipeRepo: RecipesRepo

    @Inject
    lateinit var favoritesManager: FavoritesManager
    @Inject
    lateinit var shoppingListManager:ShoppingListManager

    init {
        MyApp.sAppComponent.inject(this)
    }

    var recipes: LiveData<List<Recipe>>? = null

    fun loadData(query: String, diet: String, healthLabel: String): LiveData<List<Recipe>> {
        recipes = recipeRepo.getRecipes(query, diet, healthLabel)
        return recipes as LiveData<List<Recipe>>
    }

    /**
     * Add a recipe to the favorites
     */
    fun addToFavorite(recipe: Recipe) {
        favoritesManager.addToFavorite(recipe)
    }

    fun removeFromFavorites(recipe: Recipe) {
        favoritesManager.removeFromFavorites(recipe)
    }

    fun addToShoppingList(recipe:Recipe) {
        shoppingListManager.addRecipeToShoppingList(recipe)
    }

    fun removeFromShoppingList(recipe: Recipe) {
        shoppingListManager.removeRecipeFromShoppingList(recipe)
    }

    fun getFavorites(): LiveData<List<RecipeWithIngredients>> {
        return favoritesManager.getFavorites()
    }
}