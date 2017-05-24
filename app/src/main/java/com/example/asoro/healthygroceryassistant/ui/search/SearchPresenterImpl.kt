package com.example.asoro.healthygroceryassistant.ui.search

import com.example.asoro.healthygroceryassistant.RecipesRepo
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class SearchPresenterImpl(val mRecipesRepo: RecipesRepo) : MvpNullObjectBasePresenter<SearchView>(), SearchPresenter {

    override fun loadData(query: String, diet: String, healthLabel: String) {
        val recipeObservable: Observable<List<Recipe>> = mRecipesRepo.getRecipesRx(query,diet, healthLabel)
        recipeObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(Consumer { recipes ->
            if(!recipes.isEmpty()){
                view.showLoading(false)
                view.showResults(recipes)
            }else{

            }
        })

    }

}
