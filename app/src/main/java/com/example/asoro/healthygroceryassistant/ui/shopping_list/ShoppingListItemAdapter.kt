package com.example.asoro.healthygroceryassistant.ui.shopping_list

import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.ui.adapters.IngredientAdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ShoppingListItemAdapter(items:List<Ingredient>): ListDelegationAdapter<List<Ingredient>>() {

    init{
        delegatesManager.addDelegate(IngredientAdapterDelegate())
        setItems(items)
    }
}