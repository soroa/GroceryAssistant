package com.example.asoro.healthygroceryassistant.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.RecipesRepo
import com.example.asoro.healthygroceryassistant.model.Recipe

class SearchViewModel() : ViewModel() {

    private val recipeRepo: RecipesRepo = RecipesRepo()
    var recipes: LiveData<List<Recipe>>? = null

    fun loadData(query: String, diet: String, healthLabel: String):LiveData<List<Recipe>>{
        recipes = recipeRepo.getRecipes(query,diet,healthLabel)
        return recipes as LiveData<List<Recipe>>
    }

}