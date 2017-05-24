package com.example.asoro.healthygroceryassistant.ui.search

import com.hannesdorfmann.mosby3.mvp.MvpPresenter


interface SearchPresenter : MvpPresenter<SearchView>{



    fun loadData(query:String,diet:String="", healthLabel:String="")

}
