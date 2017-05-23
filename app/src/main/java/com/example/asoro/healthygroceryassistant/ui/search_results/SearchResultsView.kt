package com.example.asoro.healthygroceryassistant.ui.search_results

import com.example.asoro.healthygroceryassistant.model.Recipe
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by asoro on 12.05.17.
 */

interface SearchResultsView : MvpView{

    fun showRecipes(recipes:List<Recipe>)



}
