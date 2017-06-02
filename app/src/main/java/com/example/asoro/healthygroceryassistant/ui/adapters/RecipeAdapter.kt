package com.example.asoro.healthygroceryassistant.ui.adapters

import com.example.asoro.healthygroceryassistant.model.Recipe
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class RecipeAdapter(recipeTeaserActionListener: RecipeAdapterDelegate.OnRecipeTeaserActionListener, items:List<Recipe>, isShowingFavorites:Boolean) : ListDelegationAdapter<List<Recipe>>() {

    init {
        delegatesManager.addDelegate(RecipeAdapterDelegate(recipeTeaserActionListener, isShowingFavorites))
        setItems(items)
    }

}