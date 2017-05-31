package com.example.asoro.healthygroceryassistant.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.RecipesRepo
import com.example.asoro.healthygroceryassistant.db.FavoritesDatabase
import com.example.asoro.healthygroceryassistant.model.Recipe
import javax.inject.Inject

class SearchViewModel: ViewModel() {

    @Inject
    lateinit var recipeRepo: RecipesRepo

    init {
        MyApp.repositoryComponent.inject(this)
    }

    var recipes: LiveData<List<Recipe>>? = null
    var favoritesDatabase: FavoritesDatabase? = null

    fun loadData(query: String, diet: String, healthLabel: String): LiveData<List<Recipe>> {
        recipes = recipeRepo.getRecipes(query, diet, healthLabel)
        return recipes as LiveData<List<Recipe>>
    }

    fun addToFavorite(recipe: Recipe) {
        favoritesDatabase?.favoritesDao()?.insert(recipe)
    }

    fun removeFromFavorites(recipe: Recipe) {
        favoritesDatabase?.favoritesDao()?.delete(recipe)
    }


}