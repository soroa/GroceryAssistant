package com.example.asoro.healthygroceryassistant.ui.search_results


import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.example.asoro.healthygroceryassistant.ui.adapters.RecipeAdapter
import kotlinx.android.synthetic.main.fragment_recipes_search_results.*

class SearchResultsLifecycleFragment(private val recipes: List<Recipe>):LifecycleFragment(),SearchResultsView {

    var adapter: RecipeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_recipes_search_results)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {

        adapter = RecipeAdapter(recipes)
        val mLayoutManager = LinearLayoutManager(context)
        recipes_search_results_rv.setLayoutManager(mLayoutManager)
        recipes_search_results_rv.setItemAnimator(DefaultItemAnimator())
        recipes_search_results_rv.setAdapter(adapter)
    }

    override fun showRecipes(newRecipes: List<Recipe>) {
        adapter?.notifyDataSetChanged()
    }

}
