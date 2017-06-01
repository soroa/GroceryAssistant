package com.example.asoro.healthygroceryassistant.ui.search_results

import com.example.asoro.healthygroceryassistant.model.Recipe


interface SearchResultsView{

    fun showRecipes(recipes: List<Recipe>)

}
