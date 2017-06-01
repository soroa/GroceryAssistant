package com.example.asoro.healthygroceryassistant.ui.search

import com.example.asoro.healthygroceryassistant.model.Recipe

interface SearchView{

    fun showLoading(show:Boolean)

    fun showError(msg:String)

    fun showResults(recipes:List<Recipe>)

}
