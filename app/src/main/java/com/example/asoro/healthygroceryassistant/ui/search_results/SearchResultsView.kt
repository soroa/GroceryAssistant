package com.example.asoro.healthygroceryassistant.ui.search_results

import com.example.asoro.healthygroceryassistant.model.Recipe


interface SearchResultsView {
    /**
     * Display Recipes
     * @param recipes
     *          the list of recipes
     */
    fun showRecipes(recipes: List<Recipe>)

}
