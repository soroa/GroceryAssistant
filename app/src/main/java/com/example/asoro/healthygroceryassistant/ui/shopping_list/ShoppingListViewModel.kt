package com.example.asoro.healthygroceryassistant.ui.shopping_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.managers.ShoppingListManager
import com.example.asoro.healthygroceryassistant.model.Ingredient
import javax.inject.Inject

class ShoppingListViewModel: ViewModel() {

    @Inject
    lateinit var shoppingListManager: ShoppingListManager

    init {
        MyApp.sAppComponent.inject(this)
    }

    fun removeFromShoppingList(ingredient: Ingredient) {
        shoppingListManager.removeIngredientFromShoppingList(ingredient)
    }

    fun loadShoppingListIngredient(): LiveData<List<Ingredient>> {
        return shoppingListManager.getAllShoppingListItems()
    }

}