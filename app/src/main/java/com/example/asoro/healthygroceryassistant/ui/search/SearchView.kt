package com.example.asoro.healthygroceryassistant.ui.search

import com.example.asoro.healthygroceryassistant.model.Recipe
import com.hannesdorfmann.mosby3.mvp.MvpView


interface SearchView : MvpView{

    fun showLoading(show:Boolean)

    fun showError(msg:String)

    fun showResults(recipes:List<Recipe>)

}
