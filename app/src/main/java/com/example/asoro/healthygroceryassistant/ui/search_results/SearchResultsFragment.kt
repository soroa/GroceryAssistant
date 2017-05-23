package com.example.asoro.healthygroceryassistant.ui.search_results

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.RecipesRepo
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.example.asoro.healthygroceryassistant.ui.adapters.RecipeAdapter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_recipes_search_results.*




class SearchResultsFragment : MvpFragment<SearchResultsView, SearchResultsPresenter>(), SearchResultsView {

    var adapter: RecipeAdapter?=null
    var recipes: ArrayList<Recipe> =  ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_recipes_search_results)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        getPresenter().loadRecipes("chicken")

    }

    private fun initAdapter() {
        adapter = RecipeAdapter(recipes)
        val mLayoutManager = LinearLayoutManager(context)
        recipes_search_results_rv.setLayoutManager(mLayoutManager)
        recipes_search_results_rv.setItemAnimator(DefaultItemAnimator())
        recipes_search_results_rv.setAdapter(adapter)
    }


    override fun showRecipes(newRecipes: List<Recipe>) {
        recipes.addAll(newRecipes)
        adapter?.notifyDataSetChanged()

    }

    override fun createPresenter(): SearchResultsPresenter {
        return SearchResultsPresenterImpl(RecipesRepo())

    }

}
