package com.example.asoro.healthygroceryassistant.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate

class IngredientAdapterDelegate(private val listener: OnAddIngredientToShoppingListListnere) : AbsListItemAdapterDelegate<Ingredient, Ingredient, IngredientAdapterDelegate.IngredientViewHolder>() {
    override fun isForViewType(item: Ingredient, items: MutableList<Ingredient>, position: Int): Boolean {
        return items.get(position) is Ingredient
    }

    override fun onBindViewHolder(item: Ingredient, viewHolder: IngredientViewHolder, payloads: MutableList<Any>) {
        viewHolder.ingredienteCheckedView.text = item.text
        viewHolder.ingredient = item
    }

    override fun onCreateViewHolder(parent: ViewGroup): IngredientViewHolder {
        val view = parent.inflate(R.layout.recipe_teaser, false)
        return IngredientViewHolder(view, listener)
    }

    inner class IngredientViewHolder(itemView: View, listener: OnAddIngredientToShoppingListListnere) : RecyclerView.ViewHolder(itemView) {
        var ingredienteCheckedView: CheckedTextView
        var ingredient: Ingredient? = null

        init {
            ingredienteCheckedView = itemView.findViewById(R.id.ingredient_tv) as CheckedTextView
            ingredienteCheckedView.setOnClickListener({
                v ->
                when (v) {
                    ingredienteCheckedView ->
                        listener.addIngredientToShoppingListListnere(ingredient!!)
                }
            })
        }
    }

    interface OnAddIngredientToShoppingListListnere {
        fun addIngredientToShoppingListListnere(ingredient: Ingredient)
    }
}