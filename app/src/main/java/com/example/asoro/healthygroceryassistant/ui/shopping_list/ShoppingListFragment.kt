package com.example.asoro.healthygroceryassistant.ui.shopping_list

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.ui.adapters.IngredientAdapterDelegate
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener
import kotlinx.android.synthetic.main.fragment_shopping_list.*

class ShoppingListFragment : LifecycleFragment(), IngredientAdapterDelegate.OnShoppingListIngredientActionListener, SwipeableRecyclerViewTouchListener.SwipeListener {
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var ingredientAdapter: ShoppingListItemAdapter
    private lateinit var ingredients: MutableList<Ingredient>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_shopping_list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(ShoppingListViewModel::class.java)
        viewModel.loadShoppingListIngredient().observe(this, Observer { ingredients ->
            if (ingredients != null) {
                this.ingredients = ingredients as MutableList<Ingredient>
                initAdapter(ingredients)
            }
        })
    }

    override fun removeIngredientFromShoppingList(ingredient: Ingredient) {
        viewModel.removeFromShoppingList(ingredient)
    }

    override fun addIngredientToShoppingList(ingredient: Ingredient) {
    }

    override fun canSwipeLeft(position: Int): Boolean {
        return true
    }

    override fun onDismissedBySwipeLeft(recyclerView: RecyclerView?, reverseSortedPositions: IntArray?) {
        removeIngredientsFromList(reverseSortedPositions)
    }

    override fun canSwipeRight(position: Int): Boolean {
        return true
    }


    override fun onDismissedBySwipeRight(recyclerView: RecyclerView?, reverseSortedPositions: IntArray?) {
        removeIngredientsFromList(reverseSortedPositions)
    }

    private fun removeIngredientsFromList(reverseSortedPositions: IntArray?) {
        for (position in reverseSortedPositions!!) {
            viewModel.removeFromShoppingList(ingredients.removeAt(position))
            ingredientAdapter.notifyItemRemoved(position)
        }
        ingredientAdapter.notifyDataSetChanged()
    }

    private fun initAdapter(ingredients: List<Ingredient>) {
        ingredientAdapter = ShoppingListItemAdapter(ingredients)
        val mLayoutManager = LinearLayoutManager(context)
        shopping_list_rv.setLayoutManager(mLayoutManager)
        shopping_list_rv.setAdapter(ingredientAdapter)
        shopping_list_rv.addOnItemTouchListener(SwipeableRecyclerViewTouchListener(shopping_list_rv, this))
    }

}
