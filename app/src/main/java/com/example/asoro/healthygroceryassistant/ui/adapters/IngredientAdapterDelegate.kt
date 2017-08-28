package com.example.asoro.healthygroceryassistant.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate

class IngredientAdapterDelegate() : AbsListItemAdapterDelegate<Ingredient, Ingredient, IngredientAdapterDelegate.IngredientViewHolder>() {

    override fun isForViewType(item: Ingredient, items: MutableList<Ingredient>, position: Int): Boolean {
        return items.get(position) is Ingredient
    }

    override fun onBindViewHolder(item: Ingredient, viewHolder: IngredientViewHolder, payloads: MutableList<Any>) {
        viewHolder.ingredientText.text = item.text
        viewHolder.ingredient = item
    }

    override fun onCreateViewHolder(parent: ViewGroup): IngredientViewHolder {
        val view = parent.inflate(R.layout.ingredient_teaser, false)
        return IngredientViewHolder(view)
    }

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ingredientText: TextView

        lateinit var  ingredient: Ingredient
        init {
            ingredientText = itemView.findViewById(R.id.ingredient_text) as TextView
        }
    }

    interface OnShoppingListIngredientActionListener {
        fun addIngredientToShoppingList(ingredient: Ingredient)
        fun removeIngredientFromShoppingList(ingredient: Ingredient)
    }
}