package com.example.asoro.healthygroceryassistant.ui.search_results


import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.example.asoro.healthygroceryassistant.ui.recipe_detail.RecipeDetailActivity
import com.example.asoro.healthygroceryassistant.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_recipes_search_results.*

class SearchResultsFragment :LifecycleFragment(),SearchResultsView, RecipeAdapterDelegate.OnRecipeTeaserActionListener {
    var recipeAdapter: RecipeAdapter? = null

    private var  viewModel: SearchViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_recipes_search_results)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(SearchViewModel::class.java)
        initAdapter()
    }


    private fun initAdapter() {
        recipeAdapter = RecipeAdapter(this, viewModel?.recipes?.value as List<Recipe>, false)
        val mLayoutManager = LinearLayoutManager(context)
        recipes_search_results_rv.setLayoutManager(mLayoutManager)
        recipes_search_results_rv.setItemAnimator(DefaultItemAnimator())
        recipes_search_results_rv.setAdapter(recipeAdapter)
    }


    override fun showRecipes(newRecipes: List<Recipe>) {
        recipeAdapter?.notifyDataSetChanged()
    }

    override fun showRecipeDetail(recipe: Recipe) {
        var intent:Intent = Intent(context, RecipeDetailActivity::class.java)
        intent.putExtra("recipe", recipe)
        startActivity(intent)
    }

    override fun onRemoveFromFavorites(recipe: Recipe) {
        viewModel?.removeFromFavorites(recipe)
    }

    override fun onAddToFavorites(recipe:Recipe) {
        viewModel?.addToFavorite(recipe)

    }

    override fun onAddToShoppingCart(recipe:Recipe) {
        viewModel?.getFavorites()?.observe(this, Observer{recipes->
            Log.d("Andrea", "recipes")
        })
    }

}
