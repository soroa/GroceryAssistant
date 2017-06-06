package com.example.asoro.healthygroceryassistant.ui.favorites

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.example.asoro.healthygroceryassistant.ui.adapters.RecipeAdapter
import com.example.asoro.healthygroceryassistant.ui.adapters.RecipeAdapterDelegate
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : LifecycleFragment(), RecipeAdapterDelegate.OnRecipeTeaserActionListener {

    var recipeAdapter: RecipeAdapter? = null
    private var viewModel: FavoritesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_favorites)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(FavoritesViewModel::class.java)
        viewModel?.getFavorites()?.observe(this, Observer { recipes ->
            if (recipes != null) {
                initAdapter(recipes)
            }
        })
    }

    private fun initAdapter(recipes: List<Recipe>) {
        recipeAdapter = RecipeAdapter(this, recipes, true)
        val mLayoutManager = LinearLayoutManager(context)
        favorites_rv.setLayoutManager(mLayoutManager)
        favorites_rv.setItemAnimator(DefaultItemAnimator())
        favorites_rv.setAdapter(recipeAdapter)
    }

    override fun onRemoveFromFavorites(recipe: Recipe) {
        viewModel?.removeFromFavorites(recipe)
    }

    override fun onAddToFavorites(recipe:Recipe) {
         //
    }

    override fun onAddToShoppingCart(recipe:Recipe) {
        viewModel?.getFavorites()?.observe(this, Observer{recipes->
            Log.d("Andrea", "recipes")
        })
    }

    override fun showRecipeDetail(recipe: Recipe) {
//        var intent: Intent = Intent(context, RecipeDetailActivity::class.java)
//        intent.putExtra("recipe", recipe)
//        startActivity(intent)
    }
}





